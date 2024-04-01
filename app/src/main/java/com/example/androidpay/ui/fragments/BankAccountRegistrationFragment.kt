package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentBankAccountRegistrationBinding
import com.example.androidpay.databinding.FragmentSettingsBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.BankAccRegViewModel
import com.example.androidpay.ui.viewmodel.SettingsViewModel

class BankAccountRegistrationFragment : BaseFragment<BankAccRegViewModel, FragmentBankAccountRegistrationBinding>() {
    override fun getBinding(): FragmentBankAccountRegistrationBinding =
        FragmentBankAccountRegistrationBinding.inflate(layoutInflater)

    override fun initView() {
        setClickListener()
        setObservers()
    }

    private fun setClickListener(){
        //Need to check
        viewBinding.header.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewBinding.btnAddBankact.setOnClickListener { with(viewBinding) {
            viewModel.addBankAccount(etFullname.text.toString(),etAccountno.text.toString(),etIfsccode.text.toString(),
                etBankname.text.toString(),etPin.text.toString())
        } }
    }

    private fun setObservers(){
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