package com.example.androidpay.data.interfaces

import com.example.androidpay.data.model.User

interface UserRepository {
    suspend fun insertUser(user: User)

    suspend fun getUserByMobileNumber(mobileNumber: Long): User?
}