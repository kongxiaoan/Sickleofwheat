package com.tcm.sickle.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tcm.sickle.PlayActivity
import com.tcm.sickle.data.TiktokBean
import com.tcm.sickle.databinding.HomeAdapterItemBinding

/**
 * Create by kpa(billkp@yeah.net) on 2022/11/27
 * 13:43
 * Describe ：注释说明信息
 */
class HomeAdapter(val data: MutableList<TiktokBean>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(val binding: HomeAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(tiktokBean: TiktokBean) {
            binding.item = tiktokBean
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            HomeAdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            this.binding.root.setOnClickListener {
                PlayActivity.start(parent.context, data[position])
            }
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}