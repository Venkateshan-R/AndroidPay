package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentPayBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.PayViewModel


class PayFragment : BaseFragment<PayViewModel, FragmentPayBinding>() {
    override fun getBinding(): FragmentPayBinding = FragmentPayBinding.inflate(layoutInflater)
    override fun initView() {
        setClickListeners()
        setObservers()
    }

    fun setClickListeners() {
        viewBinding.btnPay.setOnClickListener {
            viewModel.transferAmount(
                viewBinding.etUpi.text.toString(), viewBinding.etAmount.text.toString(),
                viewBinding.etRemarks.text.toString()
            )
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