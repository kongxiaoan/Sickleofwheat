package com.tcm.sickle.ui.play

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import com.tcm.sickle.PlayActivity
import com.tcm.sickle.R
import com.tcm.sickle.data.TiktokBean
import com.tcm.sickle.databinding.FragmentHomeBinding
import com.tcm.sickle.databinding.FragmentPlayBinding
import com.tcm.sickle.play.FFmpegPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PlayFragment : Fragment(), SurfaceHolder.Callback {

    private var tiktokBean: TiktokBean? = null
    private var _binding: FragmentPlayBinding? = null
    private var mSurfaceHolder: SurfaceHolder? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PlayFragment()
    }

    private lateinit var viewModel: PlayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        val root: View = binding.root
        tiktokBean = activity?.intent?.getBundleExtra(PlayActivity.PLAY_BUNDLE_KEY)
            ?.getParcelable<TiktokBean>(PlayActivity.PLAY_DATA_KEY)
        mSurfaceHolder = binding.playSV.holder
        mSurfaceHolder?.addCallback(this)
        binding.playBtn.setOnClickListener {
            play()
        }
        return root
    }

    fun play() {
        val player = FFmpegPlayer()
        tiktokBean?.videoDownloadUrl?.let { mSurfaceHolder?.surface?.let { it1 ->
            player.player("http://220.161.87.62:8800/hls/0/index.m3u8",
                it1
            )
        } }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        MainScope().launch(Dispatchers.Default) {
            play()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }
}