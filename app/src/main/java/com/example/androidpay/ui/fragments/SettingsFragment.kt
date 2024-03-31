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
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.SettingsViewModel


class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {
    override fun getBinding(): FragmentSettingsBinding =
        FragmentSettingsBinding.inflate(layoutInflater)

    override fun initView() {
        context?.showToast("called")
        setUpObservers()
        setclickListeners()
    }

    fun setUpObservers() {
        viewModel.getButtonText().observe(this, Observer {
            viewBinding.btnLogin.text = it
        })

        viewModel.navigationLiveData.observe(this, Observer {
            findNavController().navigate(it)
        })

    }

    fun setclickListeners() {

        viewBinding.btnLogin.setOnClickListener {
           viewModel.onLoginButtonclick()
        }

        viewBinding.btnAddaccount.setOnClickListener {
           viewModel.onAddAccountButtonclick()
        }
    }


}