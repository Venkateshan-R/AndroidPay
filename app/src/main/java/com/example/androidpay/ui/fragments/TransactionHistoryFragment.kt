package com.example.androidpay.ui.fragments

import android.media.MediaCodec.LinearBlock
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidpay.R
import com.example.androidpay.data.interfaces.OnBackPressedListener
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.databinding.FragmentTransactionBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.base.TransactionHistoryAdapter
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.setVisible
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.TransactionHistoryViewModel

class TransactionHistoryFragment :
    BaseFragment<TransactionHistoryViewModel, FragmentTransactionBinding>(), OnBackPressedListener {
    val adapter = TransactionHistoryAdapter()
    override fun getBinding(): FragmentTransactionBinding =
        FragmentTransactionBinding.inflate(layoutInflater)

    override fun initView() {
        setUpRecyclerview()
        initObservers()
        setClickListners()
    }

    fun setClickListners() {
        viewBinding.ivClose.setOnClickListener {
            backAction()
        }
    }

    fun setUpRecyclerview() = with(viewBinding) {
        rvTransactions.layoutManager = LinearLayoutManager(context)
        adapter.userId = viewModel.getUserId()
        rvTransactions.adapter = adapter;

    }

    private fun initObservers() {
        viewModel.getAllTransactionHistory()
        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    adapter.transactionList = it.data
                    viewBinding.tvEmpty.setVisible(it.data.size == 0)
                }

                is ResultData.Failure -> context?.showToast(it.message)
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
