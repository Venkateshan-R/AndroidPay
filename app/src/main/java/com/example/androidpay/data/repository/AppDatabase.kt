package com.example.androidpay.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpay.data.model.UserDao
import com.example.androidpay.data.model.UsersModel

@Database(entities = [UsersModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): UserDao
}