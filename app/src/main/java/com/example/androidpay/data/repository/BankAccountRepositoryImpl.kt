package com.example.androidpay.data.repository

import androidx.lifecycle.LiveData
import com.example.androidpay.data.interfaces.BankAccountRepository
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.data.dao.BankAccountDao
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class BankAccountRepositoryImpl @Inject constructor(private val bankAccountDao: BankAccountDao) :
    BankAccountRepository {

    override suspend fun insertBankAccount(bankAccount: BankAccount) = bankAccountDao.insertBankAccount(bankAccount)
    override suspend fun updateBankAccount(bankAccount: BankAccount) = bankAccountDao.updateBankAccount(bankAccount)

    override suspend fun getAllBankAccount(): List<BankAccount> = bankAccountDao.getAllBankAcc()

    override fun getBankAccountsForUser(userId: Long): LiveData<BankAccount?> = bankAccountDao.getBankAccountsForUser(userId)
    override suspend fun getBankAccount(userId: Long): BankAccount? =bankAccountDao.getBankAccount(userId)

    override suspend fun getBankAccountByAccNo(accountNo: Long): BankAccount? = bankAccountDao.getBankAccountByAccNo(accountNo)
    override suspend fun getBankAccountByUpiId(upiId: String): BankAccount? = bankAccountDao.getBankAccountByUpiId(upiId)

}