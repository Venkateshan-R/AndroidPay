package com.example.androidpay.data.interfaces

import com.example.androidpay.data.model.BankAccount

interface BankAccountRepository {
    suspend fun insertBankAccount(bankAccount: BankAccount)

    suspend fun getBankAccountsForUser(userId: Int): List<BankAccount>
}