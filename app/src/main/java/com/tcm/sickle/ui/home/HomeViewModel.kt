package com.tcm.sickle.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tcm.sickle.data.DataUtil
import com.tcm.sickle.data.TiktokBean

class HomeViewModel : ViewModel() {

    private val _ticktokData = MutableLiveData<List<TiktokBean>>().apply {
        value = DataUtil.getTiktokDataFromAssets()
    }

    val ticktokData: LiveData<List<TiktokBean>> = _ticktokData
}