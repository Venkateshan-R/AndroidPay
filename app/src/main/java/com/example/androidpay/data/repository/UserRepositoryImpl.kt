package com.example.androidpay.data.repository

import com.example.androidpay.data.interfaces.UserRepository
import com.example.androidpay.data.model.User
import com.example.androidpay.data.model.UserDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun insertUser(user: User) = userDao.insertUser(user)

    override suspend fun getUserByMobileNumber(mobileNumber: Long): User? = userDao.getUserByMobileNumber(mobileNumber)
}