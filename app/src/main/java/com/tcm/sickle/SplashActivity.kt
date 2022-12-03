package com.tcm.sickle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.tcm.sickle.base.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(false)
        }
        setContentView(R.layout.activity_splash)

//        launch {
//            delay(5 * 1000)
//            HomeActivity.start(this@SplashActivity)
//            finish()
//        }
    }
}