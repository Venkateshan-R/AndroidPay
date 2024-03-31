package com.example.androidpay.ui.fragments

import com.example.androidpay.databinding.FragmentHomeBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.viewmodel.CommonViewModel


class HomeFragment : BaseFragment<CommonViewModel, FragmentHomeBinding>() {
    override fun getBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initView() {
    }


}