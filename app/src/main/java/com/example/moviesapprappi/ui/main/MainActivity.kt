package com.example.moviesapprappi.ui.main

import android.os.Bundle
import com.example.moviesapprappi.R
import com.example.moviesapprappi.applyOnPageSelected
import com.example.moviesapprappi.databinding.ActivityMainBinding
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        with(binding.mainViewpager) {
            adapter = MainPagerAdapter(supportFragmentManager, lifecycle)
            offscreenPageLimit = 3
            applyOnPageSelected { binding.mainBottomNavigation.menu.getItem(it).isChecked = true }
            binding.mainBottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_one -> currentItem = 0
                    R.id.action_two -> currentItem = 1
                }
                true
            }
        }
    }
}
