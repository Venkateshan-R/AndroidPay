package com.example.androidpay.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.androidpay.R
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.databinding.ItemTransactionHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date

class TransactionHistoryAdapter : RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {

    var transactionList: List<TransactionModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var userId: Long = 0;


    inner class ViewHolder(val binding: ItemTransactionHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(transactionModel: TransactionModel) = with(binding) {
            tvAmount.text = formatAmountTxt(transactionModel)
            tvAmount.setTextColor(getTextcolor(transactionModel))
            tvName.text = transactionModel.receiverUPIId
            tvRemarks.text = transactionModel.remarks
            tvDate.text = timestampToDayMonthYearFormat(transactionModel.transactionDate)
        }

        fun formatAmountTxt(transactionModel: TransactionModel): String =
            if (transactionModel.senderId == userId)
                "- " + getContext().getString(R.string.rupee_symbol) + transactionModel.amount
            else
                "+ " + getContext().getString(R.string.rupee_symbol) + transactionModel.amount

        fun getTextcolor(transactionModel: TransactionModel): Int =
            if (transactionModel.senderId == userId)
                getContext().getColor(R.color.amount_deducted)
            else
                getContext().getColor(R.color.amount_added)


        fun getContext() = binding.root.context

        fun timestampToDayMonthYearFormat(timestampInMilliseconds: Long): String =
            SimpleDateFormat("dd MMM yyyy").format(Date(timestampInMilliseconds))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTransactionHistoryBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = transactionList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(transactionList?.get(position)!!)
    }

}