package com.example.androidpay.data.repository

import com.example.androidpay.data.interfaces.BankAccountRepository
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.data.dao.BankAccountDao
import javax.inject.Inject

class BankAccountRepositoryImpl @Inject constructor(private val bankAccountDao: BankAccountDao) :
    BankAccountRepository {

    override suspend fun insertBankAccount(bankAccount: BankAccount) = bankAccountDao.insertBankAccount(bankAccount)

    override suspend fun getBankAccountsForUser(userId: Int): List<BankAccount> = bankAccountDao.getBankAccountsForUser(userId)
}