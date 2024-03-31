package com.example.androidpay.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpay.data.model.TransactionModel

@Dao
interface TransactionDao {
    @Insert
    suspend fun saveTransaction(transaction: TransactionModel)

    @Query("SELECT * FROM TransactionModel WHERE senderAccountId = :accountId OR receiverAccountNumber = :accountId ORDER BY transactionDate DESC")
    suspend fun getAllTransactions(accountId: Int): List<TransactionModel>

    @Query("SELECT * FROM TransactionModel WHERE senderAccountId = :accountId ORDER BY transactionDate DESC")
    suspend fun getTransactionByAccNo(accountId: Int): List<TransactionModel>


}