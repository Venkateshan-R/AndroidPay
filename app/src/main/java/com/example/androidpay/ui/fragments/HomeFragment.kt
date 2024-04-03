package com.example.androidpay.ui.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentHomeBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.setAllOnClickListener
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun getBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initView() {
        setclickListeners()
        setObservers()
    }

    fun setclickListeners(){
        viewBinding.btnAddmoney.setAllOnClickListener {
            viewModel.onAddMoneyClick()
        }
        viewBinding.btnSend.setAllOnClickListener {
            viewModel.onPayClicked()
        }
        viewBinding.btnAccount.setAllOnClickListener {
            viewModel.onAccountClicked()
        }
        viewBinding.btnTransaction.setAllOnClickListener {
            viewModel.onTransactionClicked()
        }
        viewBinding.tvSettings.setOnClickListener {
            viewModel.onSettingsClicked()
        }
    }

    fun setObservers(){
        /*viewModel.getUserBankAccount()
        viewModel.btnText.observe(this, Observer {
          //  viewBinding.tvAccbal.text = it
        })*/
        viewModel.navigationLiveData.observe(this, Observer {
            findNavController().navigate(it)
        })
    }


}