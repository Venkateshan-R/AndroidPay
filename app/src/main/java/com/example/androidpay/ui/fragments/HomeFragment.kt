package com.example.androidpay.ui.fragments

import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentHomeBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.setOnSafeClickListener
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), OnClickListener {
    override fun getBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initView() {
        setclickListeners()
        setObservers()
    }

    fun setclickListeners() = with(viewBinding) {

        ivAddmoney.setOnClickListener(this@HomeFragment)
        tvAddmoney.setOnClickListener(this@HomeFragment)

        ivSendmoney.setOnClickListener(this@HomeFragment)
        tvSend.setOnClickListener(this@HomeFragment)


        ivTransaction.setOnClickListener(this@HomeFragment)
        tvTransaction.setOnClickListener(this@HomeFragment)
        ivTransactionForward.setOnClickListener(this@HomeFragment)

        ivAccount.setOnClickListener(this@HomeFragment)
        tvBankaccounts.setOnClickListener(this@HomeFragment)
        ivAccountForward.setOnClickListener(this@HomeFragment)

        ivProfile.setOnClickListener(this@HomeFragment)
        tvProfile.setOnClickListener(this@HomeFragment)
        ivProfileForward.setOnClickListener(this@HomeFragment)

        ivSettings.setOnClickListener(this@HomeFragment)
        tvSettings.setOnClickListener(this@HomeFragment)
        ivSettingsForward.setOnClickListener(this@HomeFragment)

    }

    fun setObservers() {
        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    /*context?.showToast("Success")
                    findNavController().popBackStack()*/
                }

                is ResultData.Failure -> context?.showToast(it.message)
            }
        })
        viewModel.navigationLiveData.observe(this, Observer {
            findNavController().navigate(it)
        })
    }

    override fun onClick(v: View?) = with(viewBinding) {

        when (v) {
            tvSend, ivSendmoney -> viewModel.onPayClicked()
            tvAddmoney, ivAddmoney -> viewModel.onAddMoneyClick()
            ivTransaction, ivTransactionForward, tvTransaction -> viewModel.onTransactionClicked()
            ivSettings, tvSettings, ivSettingsForward -> viewModel.onSettingsClicked()
            ivProfile, ivProfileForward, tvProfile -> viewModel.onProfileClicked()
            ivAccount, ivAccountForward, tvBankaccounts -> viewModel.onAccountClicked()
        }
    }
}