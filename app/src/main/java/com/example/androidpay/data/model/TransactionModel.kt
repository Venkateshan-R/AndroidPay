package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DecimalFormat

@Entity
data class TransactionModel(
    val senderId: Long,
    val receiverId: Long,
    val senderUPIId: String,
    val receiverUPIId: String,
    val senderName: String,
    val receiverName: String,
    val amount: Double,
    val transactionDate: Long,
    val remarks: String,
    private @PrimaryKey(autoGenerate = true) val transactionid: Int=0

){
    fun getTransactionid()=transactionid
    public fun getFormattedBalace() : String{
        return DecimalFormat("#,###.00").format(amount)
    }
}