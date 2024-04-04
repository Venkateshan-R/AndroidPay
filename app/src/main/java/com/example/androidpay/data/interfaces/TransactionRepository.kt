package com.example.androidpay.data.interfaces

import androidx.lifecycle.LiveData
import com.example.androidpay.data.model.TransactionModel

interface TransactionRepository {
    suspend fun saveTransaction(transaction: TransactionModel)
    suspend fun getAllTransactionsByUserId(userId: Long):List<TransactionModel>

    suspend fun getAllTransactionsByUpiId(upiId: String): List<TransactionModel>
    suspend fun getAllTransactions(): List<TransactionModel>
    suspend fun getTodaysTransactions(dayStart: Long, dayEnd: Long, upiId: String): List<TransactionModel>


}