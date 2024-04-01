
package com.example.androidpay.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpay.data.model.BankAccount

@Dao
interface BankAccountDao {
    @Insert
    suspend fun insertBankAccount(bankAccount: BankAccount)

    @Query("SELECT * FROM BankAccount WHERE userId = :userId")
    fun getBankAccountsForUser(userId: Long): LiveData<BankAccount?>

    @Query("SELECT * FROM BankAccount WHERE userId = :userId")
    suspend fun getBankAccount(userId: Long): BankAccount?

    @Query("SELECT * FROM BankAccount WHERE accountNumber = :accountNo")
    suspend fun getBankAccountByAccNo(accountNo: Long): BankAccount?
    @Query("SELECT * FROM BankAccount")
    suspend fun getAllBankAcc():List<BankAccount>

    // Add other methods for updating and deleting bank accounts
}