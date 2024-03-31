package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionModel(
    @PrimaryKey(autoGenerate = true) val transactionid: Int,
    val senderAccountId: Int,
    val receiverAccountNumber: String,
    val amount: Int,
    val transactionDate: Long
)