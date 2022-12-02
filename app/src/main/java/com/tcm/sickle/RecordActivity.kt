package com.tcm.sickle

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.gyf.immersionbar.ktx.immersionBar
import com.tcm.sickle.ui.main.RecordFragment

class RecordActivity : AppCompatActivity() {

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