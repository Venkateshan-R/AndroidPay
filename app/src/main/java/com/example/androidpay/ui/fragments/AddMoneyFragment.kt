package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentAddMoneyBinding
import com.example.androidpay.databinding.FragmentPayBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.setOnSafeClickListener
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.AddMoneyViewModel
import com.example.androidpay.ui.viewmodel.PayViewModel


class AddMoneyFragment : BaseFragment<AddMoneyViewModel, FragmentAddMoneyBinding>() {
    override fun getBinding(): FragmentAddMoneyBinding =
        FragmentAddMoneyBinding.inflate(layoutInflater)

    override fun initView() {
        setClickListeners()
        setObservers()
    }

    fun setClickListeners() {

        viewBinding.header.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewBinding.btnAddamount.setOnSafeClickListener {
            viewModel.addAmount(viewBinding.etAmount.text.toString())
        }

    }

    fun setObservers() {

        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    context?.showToast("Success")
                    findNavController().popBackStack()
                }

                is ResultData.Failure -> context?.showToast(it.message)
            }
        })
    }

}