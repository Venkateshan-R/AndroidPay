package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionModel(
    val senderId: Long,
    val receiverId: Long,
    val senderUPIId: String,
    val receiverUPIId: String,
    val amount: Double,
    val transactionDate: Long,
    val remarks: String,
    private @PrimaryKey(autoGenerate = true) val transactionid: Int=0

){
    fun getTransactionid()=transactionid
}