package com.tcm.sickle.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

/**
 * Create by kpa(billkp@yeah.net) on 2022/11/27
 * 14:19
 * Describe ：注释说明信息
 */
/**
 * imageView 设置网络图片
 */
@BindingAdapter("imageUrl")
inline fun ImageView.setImageUrl(url: String?) {
    Glide.with(this)
        .load(url ?: "")
        .into(this)
}

@BindingAdapter("radiusImageUrl")
inline fun ImageView.setRadiusImageUrl(url: String?) {
    Glide.with(this)
        .load(url ?: "")
        .transform(RoundedCorners(24))
        .into(this)
}