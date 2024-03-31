package com.example.androidpay.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userModel: UsersModel)

    @Insert
    suspend fun getUserList(usersList: List<UsersModel>)

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UsersModel>

}