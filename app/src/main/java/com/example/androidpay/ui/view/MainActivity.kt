package com.example.androidpay.ui.view


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import com.example.androidpay.data.interfaces.OnBackPressedListener
import com.example.androidpay.ui.utils.showToast


class MainActivity : BaseActivity<CommonViewModel, ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        (application as MyApplication).appComponent.inject(this)
        setUpNavigation()
        initPrint()
    }

    fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_graph)

        if (viewModel.getUserId() > 0) {
            graph.setStartDestination(R.id.homeFragment)
        } else {
            graph.setStartDestination(R.id.loginFragment)
        }
        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }


    fun initPrint() {
        viewModel.logLiveData.observe(this, Observer {
            Log.d("DEBUG_DATABASE", it.toString())
        })
        viewModel.logBankLiveData.observe(this, Observer {
            Log.d("DEBUG_DATABASE", it.toString())
        })
        viewModel.logTransactionLiveData.observe(this, Observer {
            Log.d("DEBUG_DATABASE", it.toString())
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d("ondestory", "onresume")
        //for debugging purpose it will print all the datas of USER,BANK ACCOUNTS,TRANSACTION
        viewModel.getallusers()
        viewModel.getAllbankAcc()
        viewModel.getAllTransaction()
    }

    //To handle backpress in fragment
    override fun onBackPressed() {
        val fragment =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).getChildFragmentManager()
                .getFragments().get(0);
        if (fragment is OnBackPressedListener) {
            fragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}