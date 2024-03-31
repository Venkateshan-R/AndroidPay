package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAccount(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int,    // Foreign key to User
    val accountNumber: String,
    val bankName: String,
    val balance: Int,
    val perDayTransactionLimit: Int,
    val perTransactionLimit: Int
)