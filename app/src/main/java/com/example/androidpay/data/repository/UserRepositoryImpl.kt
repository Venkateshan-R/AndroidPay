package com.example.androidpay.data.repository

import com.example.androidpay.data.interfaces.UserRepository
import com.example.androidpay.data.model.User
import com.example.androidpay.data.dao.UserDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor( val userDao: UserDao) : UserRepository {

    override suspend fun insertUser(user: User): Long = userDao.insertUser(user)

    override suspend fun getUserByMobileNumber(mobileNumber: String): User? = userDao.getUserByMobileNumber(mobileNumber)
    override suspend fun getAllUser(): List<User> =
         userDao.getAllUsers()

}