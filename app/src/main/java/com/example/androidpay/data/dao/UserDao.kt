
package com.example.androidpay.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpay.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User) : Long

    @Query("SELECT * FROM User WHERE mobileNumber = :mobileNumber")
    suspend fun getUserByMobileNumber(mobileNumber: String): User?

    @Query("SELECT * FROM User ")
    suspend fun getAllUsers(): List<User>


}