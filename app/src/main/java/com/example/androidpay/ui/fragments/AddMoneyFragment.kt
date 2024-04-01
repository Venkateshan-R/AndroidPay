package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentAddMoneyBinding
import com.example.androidpay.databinding.FragmentPayBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.viewmodel.AddMoneyViewModel
import com.example.androidpay.ui.viewmodel.PayViewModel


class AddMoneyFragment : BaseFragment<AddMoneyViewModel, FragmentAddMoneyBinding>() {
    override fun getBinding(): FragmentAddMoneyBinding = FragmentAddMoneyBinding.inflate(layoutInflater)
    override fun initView() {
    }

}