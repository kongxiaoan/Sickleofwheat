package com.tcm.sickle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gyf.immersionbar.ktx.immersionBar
import com.tcm.sickle.base.BaseActivity
import com.tcm.sickle.data.TiktokBean
import com.tcm.sickle.ui.play.PlayFragment

class PlayActivity : BaseActivity() {

    companion object {
        const val PLAY_DATA_KEY = "play_data_key"
        const val PLAY_BUNDLE_KEY = "play_bundle_key"
        fun start(context: Context, tiktokBean: TiktokBean) {
            val intent = Intent(context, PlayActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(PLAY_DATA_KEY, tiktokBean)
            intent.putExtra(PLAY_BUNDLE_KEY, bundle)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(false)
        }
        setContentView(R.layout.activity_play)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PlayFragment.newInstance())
                .commitNow()
        }
    }
}