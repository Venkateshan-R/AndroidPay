package com.example.androidpay.ui.fragments

import com.example.androidpay.databinding.FragmentTransactionBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.viewmodel.CommonViewModel

class TransactionFragment : BaseFragment<CommonViewModel, FragmentTransactionBinding>() {
    override fun getBinding(): FragmentTransactionBinding = FragmentTransactionBinding.inflate(layoutInflater)

    override fun initView() {
    }


}
