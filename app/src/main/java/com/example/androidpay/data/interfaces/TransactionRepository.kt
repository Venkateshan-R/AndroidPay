package com.example.androidpay.data.interfaces

import com.example.androidpay.data.model.TransactionModel

interface TransactionRepository {
    suspend fun saveTransaction(transaction: TransactionModel)
    suspend fun getAllTransactions(accountId: Int): List<TransactionModel>

    suspend fun getTransactionByAccNo(accountId: Int): List<TransactionModel>

}