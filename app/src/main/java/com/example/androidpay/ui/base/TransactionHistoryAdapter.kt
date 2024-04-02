package com.example.androidpay.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.databinding.ItemTransactionHistoryBinding

class TransactionHistoryAdapter : RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {

    var transactionList : List<TransactionModel>?=null
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(val binding : ItemTransactionHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(transactionModel: TransactionModel)= with(binding){
            tvAmount.text = transactionModel.amount.toString()
            tvSender.text = transactionModel.senderUPIId
            tvReceiver.text = transactionModel.receiverUPIId
            tvRemarks.text = transactionModel.remarks
            tvTime.text = transactionModel.transactionDate.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    = ViewHolder(ItemTransactionHistoryBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int =transactionList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(transactionList?.get(position)!!)
    }
}