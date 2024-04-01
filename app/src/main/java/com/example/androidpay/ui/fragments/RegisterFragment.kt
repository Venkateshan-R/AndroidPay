package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentHomeBinding
import com.example.androidpay.databinding.FragmentRegisterBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.setOnSafeClickListener
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.UserViewModel


class RegisterFragment : BaseFragment<UserViewModel, FragmentRegisterBinding>() {
    override fun getBinding(): FragmentRegisterBinding =
        FragmentRegisterBinding.inflate(layoutInflater)

    override fun initView() {
        setclickListeners()
        setLiveDataObserver()
    }

    private fun setLiveDataObserver() {
        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
               is ResultData.Success -> {
                   context?.showToast("Success")
                   findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
               }
                is ResultData.Failure -> context?.showToast(it.message)
            }
        })
    }

    private fun setclickListeners() {
        //Need to check
        viewBinding.header.ivBack.setOnClickListener { findNavController().popBackStack() }
        viewBinding.btnRegister.setOnSafeClickListener {
            viewModel.registerUser(
                viewBinding.etMobile.text.toString(),
                viewBinding.etPassword.text.toString()
                ,viewBinding.etFirstname.text.toString()
                ,viewBinding.etLastname.text.toString(),
            )
        }


    }

}