#include <jni.h>
#include <string>
#include <unistd.h>
#include <android/native_window.h>
#include <android/native_window_jni.h>

extern "C" {
#include <libavutil/avutil.h>
#include "utils/logger.h"
#include <libavformat/avformat.h>
#include <libavcodec/avcodec.h>
#include <libavutil/imgutils.h>
#include "libswscale/swscale.h"
#define TAG "FFMPEG-TEST"

/**
* 拿到 ffmpeg 当前版本
* @return
*/
const char *getFFmpegVer() {
    return avutil_configuration();
}


jstring JNICALL
Java_com_tcm_sickle_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    av_log_set_level(AV_LOG_DEBUG);
    av_log(NULL, AV_LOG_INFO, "测试日志");
    LOGW(TAG, "我是谁 ")
    return env->NewStringUTF(getFFmpegVer());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_tcm_sickle_play_FFmpegPlayer_player(JNIEnv *env, jobject thiz, jstring url,
                                             jobject surface) {

    int result;
    const char *path = env->GetStringUTFChars(url, 0);
    LOGD(TAG, "地址 = %s", path)
    //初始化上下文
    AVFormatContext *format_context = avformat_alloc_context();
    //打开音频文件
    result = avformat_open_input(&format_context, path, NULL, NULL);
    if(result < 0) {
        LOGE(TAG, "打开失败 %s", av_err2str(result))
        return;
    }
    //检索流信息
    result = avformat_find_stream_info(format_context, NULL);
    if(result < 0) {
        LOGE(TAG, "找不到音频流 %s", av_err2str(result))
        return;
    }
    //找到第一个视频流
    int video_stream_index = -1, i;
    for (i = 0; i < format_context->nb_streams; ++i) {
        if(format_context->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO && video_stream_index < 0) {
            video_stream_index = i;
        }
    }
    if(video_stream_index == -1) {
        LOGE(TAG, " %s", av_err2str(video_stream_index));
        return;
    }

    //初始化解码器上下文
    AVCodecContext *video_codec_context = avcodec_alloc_context3(NULL);
    result =  avcodec_parameters_to_context(video_codec_context, format_context -> streams[video_stream_index]->codecpar);
    if(result < 0) {
        LOGE(TAG, "初始化解码器失败,%s", av_err2str(result));
        return;
    }
    // 初始化视频编码器
    const AVCodec *video_codec = avcodec_find_decoder(video_codec_context->codec_id);
    if (video_codec == NULL) {
        LOGE(TAG, "Player Error : Can not find video codec");
        return;
    }

    // 打开视频解码器
    result = avcodec_open2(video_codec_context,video_codec, nullptr);
    if(result < 0) {
        LOGE(TAG, "打开视频解码器失败 %s", av_err2str(result));
        return;
    }

// 获取视频的宽高
    int videoWidth = video_codec_context->width;
    int videoHeight = video_codec_context->height;
    // R4 初始化 Native Window 用于播放视频
    ANativeWindow *native_window = ANativeWindow_fromSurface(env, surface);
    if (native_window == NULL) {
        LOGE(TAG,"Player Error : Can not create native window");
        return;
    }
    // 通过设置宽高限制缓冲区中的像素数量，而非屏幕的物理显示尺寸。
    // 如果缓冲区与物理屏幕的显示尺寸不相符，则实际显示可能会是拉伸，或者被压缩的图像
    result = ANativeWindow_setBuffersGeometry(native_window, videoWidth, videoHeight,WINDOW_FORMAT_RGBA_8888);
    if (result < 0){
        LOGE(TAG,"Player Error : Can not set native window buffer");
        ANativeWindow_release(native_window);
        return;
    }
    // 定义绘图缓冲区
    ANativeWindow_Buffer window_buffer;
    // 声明数据容器 有3个
    // R5 解码前数据容器 Packet 编码数据
    AVPacket *packet = av_packet_alloc();
    // R6 解码后数据容器 Frame 像素数据 不能直接播放像素数据 还要转换
    AVFrame *frame = av_frame_alloc();
    // R7 转换后数据容器 这里面的数据可以用于播放
    AVFrame *rgba_frame = av_frame_alloc();
    // 数据格式转换准备
    // 输出 Buffer
    int buffer_size = av_image_get_buffer_size(AV_PIX_FMT_RGBA, videoWidth, videoHeight, 1);
    // R8 申请 Buffer 内存
    uint8_t *out_buffer = (uint8_t *) av_malloc(buffer_size * sizeof(uint8_t));
    av_image_fill_arrays(rgba_frame->data, rgba_frame->linesize, out_buffer, AV_PIX_FMT_RGBA, videoWidth, videoHeight, 1);
    // R9 数据格式转换上下文
    struct SwsContext *data_convert_context = sws_getContext(
            videoWidth, videoHeight, video_codec_context->pix_fmt,
            videoWidth, videoHeight, AV_PIX_FMT_RGBA,
            SWS_BICUBIC, NULL, NULL, NULL);
    // 开始读取帧
    while (av_read_frame(format_context, packet) >= 0) {
        // 匹配视频流
        if (packet->stream_index == video_stream_index) {
            // 解码
            result = avcodec_send_packet(video_codec_context, packet);
            if (result < 0 && result != AVERROR(EAGAIN) && result != AVERROR_EOF) {
                LOGE(TAG,"Player Error : codec step 1 fail");
//                return;
            }
            result = avcodec_receive_frame(video_codec_context, frame);
            if (result < 0 && result != AVERROR_EOF) {
                LOGE(TAG,"Player Error : codec step 2 fail");
//                return;
            }
            // 数据格式转换
            result = sws_scale(
                    data_convert_context,
                    (const uint8_t* const*) frame->data, frame->linesize,
                    0, videoHeight,
                    rgba_frame->data, rgba_frame->linesize);
            if (result <= 0) {
                LOGE(TAG,"Player Error : data convert fail");
//                return;
            }
            // 播放
            result = ANativeWindow_lock(native_window, &window_buffer, NULL);
            if (result < 0) {
                LOGE(TAG,"Player Error : Can not lock native window");
            } else {
                // 将图像绘制到界面上
                // 注意 : 这里 rgba_frame 一行的像素和 window_buffer 一行的像素长度可能不一致
                // 需要转换好 否则可能花屏
                uint8_t *bits = (uint8_t *) window_buffer.bits;
                for (int h = 0; h < videoHeight; h++) {
                    memcpy(bits + h * window_buffer.stride * 4,
                           out_buffer + h * rgba_frame->linesize[0],
                           rgba_frame->linesize[0]);
                }
                ANativeWindow_unlockAndPost(native_window);
            }
        }
        // 释放 packet 引用
        av_packet_unref(packet);
    }
    // 释放 R9
    sws_freeContext(data_convert_context);
    // 释放 R8
    av_free(out_buffer);
    // 释放 R7
    av_frame_free(&rgba_frame);
    // 释放 R6
    av_frame_free(&frame);
    // 释放 R5
    av_packet_free(&packet);
    // 释放 R4
    ANativeWindow_release(native_window);
    // 关闭 R3
    avcodec_close(video_codec_context);
    // 释放 R2
    avformat_close_input(&format_context);
    // 释放 R1
    env->ReleaseStringUTFChars(url, path);
}

}

