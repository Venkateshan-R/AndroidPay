package com.example.androidpay.ui.view


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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
        navHostFragment.navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                when(destination.id){
                    R.id.homeFragment ,
                    R.id.settingsFragment ,
                    R.id.transactionFragment -> showBottomNavigationView()
                    else -> hideBottomNavigationView()
                }
            }

        })

        initPrint()

    }

    fun initPrint(){
        viewModel.logLiveData.observe(this, Observer {
            Log.d("RoomDatas",it.toString())
        })
        viewModel.logBankLiveData.observe(this, Observer {
            Log.d("RoomDatas",it.toString())
        })
        viewModel.logTransactionLiveData.observe(this, Observer {
            Log.d("RoomDatas",it.toString())
        })
    }

    fun showBottomNavigationView() {
        viewBinding.bottomNavigation.visibility = View.VISIBLE
    }
    fun hideBottomNavigationView() {
        viewBinding.bottomNavigation.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.getallusers()
        viewModel.getAllbankAcc()
        viewModel.getAllTransaction()
    }

}