package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentBankDetailsBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.DataHolder
import com.example.androidpay.ui.viewmodel.BankAccRegViewModel


class BankDetailsFragment : BaseFragment<BankAccRegViewModel, FragmentBankDetailsBinding>() {
    override fun getBinding(): FragmentBankDetailsBinding =
        FragmentBankDetailsBinding.inflate(layoutInflater)

    override fun initView() {
        setClickListeners()
        setObservers()
    }

    fun setClickListeners() {
        //need to check to handle add transaction
        viewBinding.ivClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun setObservers() {
        viewModel.getUserBankAccount()
        viewModel.valuesTxt.observe(this, Observer {
            when ((it as DataHolder.Datas).tag) {
                BANK_DATAS.AMOUNT -> viewBinding.tvBalanceValue.text = it.value
                BANK_DATAS.NAME -> viewBinding.tvNameValue.text = it.value
                BANK_DATAS.ACCOUNT_NUMBER -> viewBinding.tvAccountnoValue.text = it.value
                BANK_DATAS.IFSC_CODE -> viewBinding.tvIfscValue.text = it.value
                BANK_DATAS.UPI -> viewBinding.tvUpiValue.text = it.value
                BANK_DATAS.PIN -> viewBinding.tvPinValue.text = it.value
                BANK_DATAS.BANK_NAME -> {}
            }
        })
    }

    enum class BANK_DATAS {
        NAME, AMOUNT, ACCOUNT_NUMBER, IFSC_CODE, UPI, PIN,BANK_NAME
    }

}