package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentProfileBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.UserViewModel

class ProfileFragment : BaseFragment<UserViewModel, FragmentProfileBinding>() {
    override fun getBinding(): FragmentProfileBinding =
        FragmentProfileBinding.inflate(layoutInflater)

    override fun initView() {
        setClickListeners()
        setObservers()
        viewModel.getCurrentUser()
    }

    fun setObservers() {
        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    with(viewBinding) {
                        tvFirstnameValue.text = it.data.firstName
                        tvLastnameValue.text = it.data.lastName
                        tvMobileValue.text = it.data.mobileNumber
                    }
                }

                is ResultData.Failure -> {}
            }
        })

        viewModel.navigationLiveData.observe(this, Observer {
            it?.let {
                findNavController().navigate(it)
            } ?: findNavController().popBackStack()
        })
    }

    fun setClickListeners() {
        viewBinding.ivClose.setOnClickListener { viewModel.onBackPressesed() }
        viewBinding.btnLogout.setOnClickListener { viewModel.logout() }
    }
}