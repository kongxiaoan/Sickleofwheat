package com.tcm.sickle

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.tcm.sickle.base.ActivityLifecycleCallbackImpl

/**
 * Create by kpa(billkp@yeah.net) on 2022/11/27
 * 13:37
 * Describe ：注释说明信息
 */
class SFApplication: Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Glide.init(this, GlideBuilder())
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
    }
}