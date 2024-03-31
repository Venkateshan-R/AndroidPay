package com.example.androidpay.data.repository

import com.example.androidpay.data.interfaces.TransactionRepository
import com.example.androidpay.data.dao.TransactionDao
import com.example.androidpay.data.model.TransactionModel
import javax.inject.Inject

class TransactionRepositoryImpl@Inject constructor(private val transactionDao: TransactionDao) : TransactionRepository {
    override suspend fun saveTransaction(transaction: TransactionModel) {
        transactionDao.saveTransaction(transaction)
    }

    override suspend fun getAllTransactions(accountId: Int): List<TransactionModel> {
        return transactionDao.getAllTransactions(accountId)
    }

    override suspend fun getTransactionByAccNo(accountId: Int): List<TransactionModel> {
        return transactionDao.getTransactionByAccNo(accountId)
    }
}