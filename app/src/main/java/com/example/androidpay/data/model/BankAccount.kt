package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidpay.ui.utils.Constants

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
    var balance: Double = 0.0,
    val perDayTransactionLimit: Int = Constants.PER_DAY_TRANSACTION_LIMIT,
    val perTransactionLimit: Int=  Constants.PER_TRANSACTION_LIMIT,
){
    fun getId(): Int {
        return id
    }
}