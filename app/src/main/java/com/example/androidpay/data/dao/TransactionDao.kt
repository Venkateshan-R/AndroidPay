package com.example.androidpay.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpay.data.model.TransactionModel

@Dao
interface TransactionDao {
    @Insert
    suspend fun saveTransaction(transaction: TransactionModel)

    @Query("SELECT * FROM TransactionModel WHERE senderId = :userId OR receiverId = :userId ORDER BY transactionDate DESC")
    fun getAllTransactions(userId: Long): LiveData<List<TransactionModel>>

    @Query("SELECT * FROM TransactionModel")
    suspend fun getAllTransactions(): List<TransactionModel>

    @Query("SELECT * FROM TransactionModel WHERE senderUPIId = :upiId OR receiverUPIId = :upiId ORDER BY transactionDate DESC")
    suspend fun getTransactionByUpiId(upiId: String): List<TransactionModel>
    @Query("SELECT * FROM TransactionModel WHERE (senderUPIId = :upiId AND receiverUPIId != :upiId) OR (receiverUPIId = :upiId AND senderUPIId != :upiId) AND transactionDate >= :dayStart AND transactionDate < :dayEnd")
    suspend fun getTodaysTransactions(dayStart: Long, dayEnd: Long,upiId: String): List<TransactionModel>


}