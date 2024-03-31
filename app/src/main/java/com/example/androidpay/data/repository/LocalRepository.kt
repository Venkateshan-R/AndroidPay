package com.example.androidpay.data.repository


import com.example.androidpay.data.model.UserDao
import com.example.androidpay.data.model.UsersModel
import javax.inject.Inject

class LocalRepository @Inject constructor() {

    @Inject
    lateinit var usersDao: UserDao
    fun getAllNotes() = usersDao.getAllUsers()
    suspend fun updateNotes(usersModel: UsersModel) = usersDao.insertUser(usersModel)
}