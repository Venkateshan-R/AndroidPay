package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentHomeBinding
import com.example.androidpay.databinding.FragmentSettingsBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.SettingsViewModel


class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {
    override fun getBinding(): FragmentSettingsBinding =
        FragmentSettingsBinding.inflate(layoutInflater)

    override fun initView() {
        setUpObservers()
        setclickListeners()
        viewModel.getUserBankAccount()
    }

    fun setUpObservers() {

        viewModel.navigationLiveData.observe(this, Observer {
            it?.let {
                findNavController().navigate(it)
            } ?: let { onBackClicked() }
        })

        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    viewBinding.etPerTransaction.setText(it.data.perTransactionLimit.toString())
                    viewBinding.etPerdayTransaction.setText(it.data.perDayTransactionLimit.toString())
                }
                is ResultData.Failure -> context?.showToast(it.message)
            }
        })

    }

    fun setclickListeners() {

        viewBinding.btnSave.setOnClickListener {
            viewModel.onSaveclick(
                viewBinding.etPerTransaction.text.toString(),
                viewBinding.etPerdayTransaction.text.toString()
            )
        }

        viewBinding.ivClose.setOnClickListener {
            onBackClicked()
        }


    }

    fun onBackClicked() {
        findNavController().popBackStack()
    }


}