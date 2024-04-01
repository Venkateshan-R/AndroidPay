package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAccount(
    private @PrimaryKey(autoGenerate = true) val id: Int=0,
    val userId: Long,
    val userFullName: String,
    val accountNumber: Long,
    val ifscCode: String,
    val bankName: String,
    val PIN: Int,
    val upiId: String,
    val balance: Long = 0,
    val perDayTransactionLimit: Int = 100000,
    val perTransactionLimit: Int= 50000,
){
    fun getId(): Int {
        return id
    }
}