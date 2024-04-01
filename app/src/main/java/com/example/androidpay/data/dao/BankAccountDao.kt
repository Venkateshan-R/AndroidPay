
package com.example.androidpay.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidpay.data.model.BankAccount

@Dao
interface BankAccountDao {
    @Insert
    suspend fun insertBankAccount(bankAccount: BankAccount)

    @Update
    suspend fun updateBankAccount(bankAccount: BankAccount)

    @Query("SELECT * FROM BankAccount WHERE userId = :userId")
    fun getBankAccountsForUser(userId: Long): LiveData<BankAccount?>

    @Query("SELECT * FROM BankAccount WHERE userId = :userId")
    suspend fun getBankAccount(userId: Long): BankAccount?

    @Query("SELECT * FROM BankAccount WHERE accountNumber = :accountNo")
    suspend fun getBankAccountByAccNo(accountNo: Long): BankAccount?
    @Query("SELECT * FROM BankAccount WHERE upiId = :upiId")
    suspend fun getBankAccountByUpiId(upiId: String): BankAccount?
    @Query("SELECT * FROM BankAccount")
    suspend fun getAllBankAcc():List<BankAccount>

}