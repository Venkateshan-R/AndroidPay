package com.example.androidpay.ui.fragments

import android.media.MediaCodec.LinearBlock
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.databinding.FragmentTransactionBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.base.TransactionHistoryAdapter
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.TransactionHistoryViewModel

class TransactionHistoryFragment : BaseFragment<TransactionHistoryViewModel, FragmentTransactionBinding>() {
    val adapter = TransactionHistoryAdapter()
    override fun getBinding(): FragmentTransactionBinding = FragmentTransactionBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerview()
        initObservers()
    }

    fun setUpRecyclerview()= with(viewBinding){
        rvTransactions.layoutManager = LinearLayoutManager(context)
        rvTransactions.adapter = adapter;

    }

    private fun initObservers(){
        viewModel.getAllTransactionHistory()
        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    context?.showToast("Success")
                    adapter.transactionList = it.data
                }
                is ResultData.Failure -> context?.showToast(it.message)
            }
        })
    }


}
