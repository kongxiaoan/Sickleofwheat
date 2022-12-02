package com.tcm.sickle

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gyf.immersionbar.ktx.immersionBar
import com.tcm.sickle.databinding.ActivityHomeAvtivityBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeAvtivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immersionBar {
            statusBarColor(R.color.transparent)
            statusBarDarkFont(true)
        }
        binding = ActivityHomeAvtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home_avtivity)

        navView.setupWithNavController(navController)
    }
}