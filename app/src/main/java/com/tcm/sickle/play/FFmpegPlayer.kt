package com.tcm.sickle.play

import android.view.Surface

/**
 * Create by kpa(billkp@yeah.net) on 2022/11/27
 * 15:58
 * Describe ：注释说明信息
 */
class FFmpegPlayer {
    companion object {
        init {
            System.loadLibrary("sickle")
        }
    }

    external fun player(url:String, surface: Surface)
}