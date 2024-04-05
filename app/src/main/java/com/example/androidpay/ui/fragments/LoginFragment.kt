package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentLoginBinding
import com.example.androidpay.databinding.FragmentRegisterBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.setOnSafeClickListener
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.UserViewModel

class LoginFragment : BaseFragment<UserViewModel, FragmentLoginBinding>() {
    override fun getBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    override fun initView() {
        setclickListeners()
        setLiveDataObserver();
    }

    private fun setLiveDataObserver() {
        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    context?.showToast(it.message)
                }

                is ResultData.Failure -> context?.showToast(it.message)
            }
        })
        viewModel.navigationLiveData.observe(this, Observer {
            it?.let {
                findNavController().navigate(it)
            } ?: findNavController().popBackStack()
        })
    }

    private fun setclickListeners() {
        //Need to check
        //viewBinding.ivClose.setOnClickListener { findNavController().popBackStack() }
        viewBinding.tvNewuser.setOnClickListener { viewModel.onRegisterClicked() }
        viewBinding.btnLogin.setOnSafeClickListener {
            viewModel.loginUser(
                viewBinding.etMobile.text.toString(),
                viewBinding.etPassword.text.toString(),
            )
        }

    }


}