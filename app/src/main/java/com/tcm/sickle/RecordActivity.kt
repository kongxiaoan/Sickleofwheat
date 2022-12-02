package com.tcm.sickle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.tcm.sickle.base.BaseActivity
import com.tcm.sickle.ui.record.RecordFragment

class RecordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(false)
        }
        setContentView(R.layout.activity_record)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecordFragment.newInstance())
                .commitNow()
        }
    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RecordActivity::class.java))
        }
    }
}