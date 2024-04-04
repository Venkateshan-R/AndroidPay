package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.data.interfaces.OnBackPressedListener
import com.example.androidpay.databinding.FragmentBankDetailsBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.BankAccRegViewModel


class BankDetailsFragment : BaseFragment<BankAccRegViewModel, FragmentBankDetailsBinding>(),
    OnBackPressedListener {
    override fun getBinding(): FragmentBankDetailsBinding =
        FragmentBankDetailsBinding.inflate(layoutInflater)

    override fun initView() {
        setClickListeners()
        setObservers()
    }

    fun setClickListeners() {
        viewBinding.ivClose.setOnClickListener {
            backAction()
        }
    }

    fun setObservers() {
        viewModel.getUserBankAccount()
        viewModel.bankAccountLiveData.observe(this, Observer {
            with(viewBinding) {
                tvBalanceValue.text = getString(R.string.rupee_symbol) + it.getFormattedBalace()
                tvNameValue.text = it.userFullName
                tvAccountnoValue.text = it.accountNumber.toString()
                tvIfscValue.text = it.ifscCode
                tvUpiValue.text = it.upiId
                tvBanknameValue.text = it.bankName
                tvPinValue.text = it.PIN.toString()
            }
        })
    }

    override fun onBackPressed() {
        backAction()
    }

    fun backAction() {
        findNavController().popBackStack(R.id.homeFragment, false)
    }

}