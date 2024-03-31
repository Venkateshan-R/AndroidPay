package com.example.androidpay.data.interfaces

import androidx.lifecycle.LiveData
import com.example.androidpay.data.model.User

interface UserRepository {
    suspend fun insertUser(user: User) : Long
    suspend fun getUserByMobileNumber(mobileNumber: String): User?
    suspend fun getAllUser(): List<User>
}