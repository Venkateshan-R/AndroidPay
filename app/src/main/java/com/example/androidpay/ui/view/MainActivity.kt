package com.example.androidpay.ui.view


import com.example.androidpay.databinding.ActivityMainBinding
import com.example.androidpay.ui.base.BaseActivity
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.viewmodel.CommonViewModel

class MainActivity : BaseActivity<CommonViewModel, ActivityMainBinding>() {
    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        (application as MyApplication).appComponent.inject(this)
    }


}