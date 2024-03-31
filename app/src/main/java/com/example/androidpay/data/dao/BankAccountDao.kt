
package com.example.androidpay.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpay.data.model.BankAccount

@Dao
interface BankAccountDao {
    @Insert
    suspend fun insertBankAccount(bankAccount: BankAccount)

    @Query("SELECT * FROM BankAccount WHERE userId = :userId")
    suspend fun getBankAccountsForUser(userId: Int): List<BankAccount>

    // Add other methods for updating and deleting bank accounts
}