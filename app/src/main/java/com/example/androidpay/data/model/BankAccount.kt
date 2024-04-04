package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidpay.ui.utils.Constants
import java.text.DecimalFormat

@Entity
data class BankAccount(
    @PrimaryKey(autoGenerate = true) private val id: Int = 0,
    val userId: Long,
    val userFullName: String,
    val accountNumber: Long,
    val ifscCode: String,
    val bankName: String,
    val PIN: Int,
    val upiId: String,
    var perDayTransactionLimit: Double = Constants.PER_DAY_TRANSACTION_LIMIT,
    var perTransactionLimit: Double = Constants.PER_TRANSACTION_LIMIT,
    var balance: Double = 0.0
) {

    public fun addBalance(deposit : Double)
    {
        balance+=deposit
    }
    public fun deductBalance(deduction : Double)
    {
        balance-=deduction
    }

    public fun getFormattedBalace() : String{
        return DecimalFormat("#,###.00").format(balance)
    }

    fun getId(): Int {
        return id
    }
}