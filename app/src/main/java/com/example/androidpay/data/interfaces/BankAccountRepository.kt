package com.example.androidpay.data.interfaces

import androidx.lifecycle.LiveData
import com.example.androidpay.data.model.BankAccount

interface BankAccountRepository {
    suspend fun insertBankAccount(bankAccount: BankAccount)
    suspend fun updateBankAccount(bankAccount: BankAccount)
    suspend fun getAllBankAccount(): List<BankAccount>
    suspend fun getBankAccount(userId: Long): BankAccount?
    suspend fun getBankAccountByAccNo(accountNo: Long): BankAccount?
    suspend fun getBankAccountByUpiId(upiId : String): BankAccount?
}