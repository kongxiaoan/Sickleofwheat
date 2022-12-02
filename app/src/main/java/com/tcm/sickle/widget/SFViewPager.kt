package com.tcm.sickle.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Create by kpa(billkp@yeah.net) on 2022/11/27
 * 12:27
 * Describe ：注释说明信息
 */
class SFViewPager : ViewPager {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}