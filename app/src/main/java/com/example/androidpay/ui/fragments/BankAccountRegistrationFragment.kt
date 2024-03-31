package com.example.androidpay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentBankAccountRegistrationBinding
import com.example.androidpay.databinding.FragmentSettingsBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.viewmodel.BankAccRegViewModel
import com.example.androidpay.ui.viewmodel.SettingsViewModel

class BankAccountRegistrationFragment : BaseFragment<BankAccRegViewModel, FragmentBankAccountRegistrationBinding>() {
    override fun getBinding(): FragmentBankAccountRegistrationBinding =
        FragmentBankAccountRegistrationBinding.inflate(layoutInflater)

    override fun initView() {

    }


}