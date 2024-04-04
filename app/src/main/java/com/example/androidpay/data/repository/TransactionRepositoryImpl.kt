package com.example.androidpay.data.repository

import androidx.lifecycle.LiveData
import com.example.androidpay.data.interfaces.TransactionRepository
import com.example.androidpay.data.dao.TransactionDao
import com.example.androidpay.data.model.TransactionModel
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao) :
    TransactionRepository {
    override suspend fun saveTransaction(transaction: TransactionModel) {
        transactionDao.saveTransaction(transaction)
    }

    suspend override fun getAllTransactionsByUserId(userId: Long): List<TransactionModel> =
        transactionDao.getAllTransactions(userId)

    override suspend fun getAllTransactionsByUpiId(upiId: String): List<TransactionModel> =
        transactionDao.getTransactionByUpiId(upiId)

    override suspend fun getAllTransactions(): List<TransactionModel> = transactionDao.getAllTransactions()
    override suspend fun getTodaysTransactions(
        dayStart: Long,
        dayEnd: Long,
        upiId: String
    ): List<TransactionModel> = transactionDao.getTodaysTransactions(dayStart,dayEnd,upiId)

}