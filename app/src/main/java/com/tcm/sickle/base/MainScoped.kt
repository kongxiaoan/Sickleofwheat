package com.tcm.sickle.base

import android.app.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import java.util.*

interface MainScoped {

    companion object {
        internal val scopeMap = IdentityHashMap<MainScoped, kotlinx.coroutines.MainScope>()
    }
    val mainScope: CoroutineScope
        get() = scopeMap[this as Activity]!!

    fun createScope(){
        //或者改为 lazy 实现，即用到时再创建
        val activity = this as Activity
        scopeMap[activity] ?: MainScope().also { scopeMap[activity] = it }
    }

    fun destroyScope(){
        scopeMap.remove(this as Activity)?.cancel()
    }
}