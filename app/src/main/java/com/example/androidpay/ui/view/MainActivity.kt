package com.example.androidpay.ui.view


import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.androidpay.R
import com.example.androidpay.databinding.ActivityMainBinding
import com.example.androidpay.ui.base.BaseActivity
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupWithNavController


class MainActivity : BaseActivity<CommonViewModel, ActivityMainBinding>() {
    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        (application as MyApplication).appComponent.inject(this)

        val navHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigation = viewBinding.bottomNavigation
        bottomNavigation.setupWithNavController(navHostFragment.navController)


    }


}