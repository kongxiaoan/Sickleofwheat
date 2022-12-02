package com.tcm.sickle.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

class TiktokBean() : Parcelable {
    /**
     * authorImgUrl : https://p3-dy.byteimg.com/aweme/100x100/1e19c00014b66991215ba.jpeg
     * authorName : 相相啊
     * authorSex : 0
     * coverImgUrl : http://p9-dy.byteimg.com/large/tos-cn-p-0015/13f8180ea2bd44779449b82702b4e47b.jpeg
     * createTime : 1571047431000
     * dynamicCover : https://p9-dy.byteimg.com/obj/tos-cn-p-0015/9991ae92508340ae9b71f132b52a6c70
     * filterMusicNameStr : 青柠（手机摄影）@卡点音乐模版08
     * filterTitleStr : 昨天刷到抖音今天就马不停蹄去买了好多人排队#南洋大师...
     * filterUserNameStr : 相相啊
     * formatLikeCountStr :
     * formatPlayCountStr : 82
     * formatTimeStr : 2019.10.14
     * likeCount : 301
     * musicAuthorName : 青柠（手机摄影）
     * musicImgUrl : https://p3-dy.byteimg.com/aweme/100x100/2dce4000796963dec1a63.jpeg
     * musicName : 卡点音乐模版08
     * playCount : 82
     * title : 昨天刷到抖音今天就马不停蹄去买了  好多人排队  #南洋大师傅 @抖音小助手
     * type : 0
     * videoDownloadUrl : https://aweme.snssdk.com/aweme/v1/play/?video_id=v0300fd00000bmi4fdabr0flkdiivurg&line=0&ratio=540p&watermark=1&media_type=4&vr_type=0&improve_bitrate=0&logo_name=aweme
     * videoHeight : 720
     * videoPlayUrl : http://v1-dy.ixigua.com/4413f7d529ce8e0d0a60ce24e84ab535/5da4a52a/video/m/220b506942d480c47ada07abbc83b49a4581163dc7510000041d7a9a627d/?a=1128&br=1570&cr=0&cs=0&dr=0&ds=6&er=&l=20191014234102010155051013879290&lr=&rc=anhsZ28zZWc3cDMzM2kzM0ApZ2YzOjo5NDszNzY6ZDo5O2dvc2ZoY29eY2ZfLS1hLTBzcy4xM2IuMi1jLy8zMC8wMzA6Yw%3D%3D
     * videoWidth : 720
     */
    var authorImgUrl: String? = null
    var authorName: String? = null
    var authorSex = 0
    var coverImgUrl: String? = null
    var createTime: Long = 0
    var dynamicCover: String? = null
    var filterMusicNameStr: String? = null
    var filterTitleStr: String? = null
    var filterUserNameStr: String? = null
    var formatLikeCountStr: String? = null
    var formatPlayCountStr: String? = null
    var formatTimeStr: String? = null
    var likeCount = 0
    var musicAuthorName: String? = null
    var musicImgUrl: String? = null
    var musicName: String? = null
    var playCount = 0
    var title: String? = null
    var type = 0
    var videoDownloadUrl: String? = null
    var videoHeight = 0
    var videoPlayUrl: String? = null
    var videoWidth = 0

    constructor(parcel: Parcel) : this() {
        authorImgUrl = parcel.readString()
        authorName = parcel.readString()
        authorSex = parcel.readInt()
        coverImgUrl = parcel.readString()
        createTime = parcel.readLong()
        dynamicCover = parcel.readString()
        filterMusicNameStr = parcel.readString()
        filterTitleStr = parcel.readString()
        filterUserNameStr = parcel.readString()
        formatLikeCountStr = parcel.readString()
        formatPlayCountStr = parcel.readString()
        formatTimeStr = parcel.readString()
        likeCount = parcel.readInt()
        musicAuthorName = parcel.readString()
        musicImgUrl = parcel.readString()
        musicName = parcel.readString()
        playCount = parcel.readInt()
        title = parcel.readString()
        type = parcel.readInt()
        videoDownloadUrl = parcel.readString()
        videoHeight = parcel.readInt()
        videoPlayUrl = parcel.readString()
        videoWidth = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(authorImgUrl)
        parcel.writeString(authorName)
        parcel.writeInt(authorSex)
        parcel.writeString(coverImgUrl)
        parcel.writeLong(createTime)
        parcel.writeString(dynamicCover)
        parcel.writeString(filterMusicNameStr)
        parcel.writeString(filterTitleStr)
        parcel.writeString(filterUserNameStr)
        parcel.writeString(formatLikeCountStr)
        parcel.writeString(formatPlayCountStr)
        parcel.writeString(formatTimeStr)
        parcel.writeInt(likeCount)
        parcel.writeString(musicAuthorName)
        parcel.writeString(musicImgUrl)
        parcel.writeString(musicName)
        parcel.writeInt(playCount)
        parcel.writeString(title)
        parcel.writeInt(type)
        parcel.writeString(videoDownloadUrl)
        parcel.writeInt(videoHeight)
        parcel.writeString(videoPlayUrl)
        parcel.writeInt(videoWidth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TiktokBean> {
        override fun createFromParcel(parcel: Parcel): TiktokBean {
            return TiktokBean(parcel)
        }

        override fun newArray(size: Int): Array<TiktokBean?> {
            return arrayOfNulls(size)
        }
    }
}