package com.tcm.sickle.ui.record

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tcm.sickle.RecordActivity
import com.tcm.sickle.databinding.FragmentStartRecordBinding

class StartRecordFragment : Fragment() {

    private var _binding: FragmentStartRecordBinding? = null

    private val permissionManifest = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val PERMISSION_REQUEST_CODE = 0x001

    private var mCamera: Camera? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(StartRecordViewModel::class.java)

        _binding = FragmentStartRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bindEvent()
        return root
    }

    private fun bindEvent() {
        binding.recordBtn.setOnClickListener {
            RecordActivity.start(requireActivity())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        permissionCheck()
    }

    private fun permissionCheck() {
        if (Build.VERSION.SDK_INT >= 23) {
            var permissionState = true
            for (permission in permissionManifest) {
                if (ContextCompat.checkSelfPermission(requireActivity(), permission)
                    !== PackageManager.PERMISSION_GRANTED
                ) {
                    permissionState = false
                }
            }
            if (!permissionState) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    permissionManifest,
                    PERMISSION_REQUEST_CODE
                )
            } else {

            }
        } else {

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    if (Manifest.permission.CAMERA == permissions[i]) {

                    } else if (Manifest.permission.RECORD_AUDIO == permissions[i]) {
                    }
                }
            }
        }
    }

}