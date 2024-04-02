package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidpay.ui.utils.Constants

@Entity
data class BankAccount(
    private @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Long,
    val userFullName: String,
    val accountNumber: Long,
    val ifscCode: String,
    val bankName: String,
    val PIN: Int,
    val upiId: String,
    val perDayTransactionLimit: Int = Constants.PER_DAY_TRANSACTION_LIMIT,
    val perTransactionLimit: Int = Constants.PER_TRANSACTION_LIMIT,
) {

    private var balance: Double = 0.0

    fun getBalance() = balance
    fun setBalance(balance : Double){
        this.balance = balance
    }



    public fun addBalance(deposit : Double)
    {
        balance+=deposit
    }
    public fun deductBalance(deduction : Double)
    {
        balance-=deduction
    }

    fun getId(): Int {
        return id
    }
}