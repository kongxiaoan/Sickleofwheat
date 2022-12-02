package com.tcm.sickle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.tcm.sickle.databinding.ActivityMainBinding
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(false)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
//        binding.sampleText.text = stringFromJNI()
        thread {
//            val uri = "android.resource://" + packageName + "/" + R.raw.ffmpeg_1
        }
    }

    /**
     * A native method that is implemented by the 'sickle' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun parseMp4(url: String): String

    companion object {
        // Used to load the 'sickle' library on application startup.
        init {
            System.loadLibrary("sickle")
        }
    }
}