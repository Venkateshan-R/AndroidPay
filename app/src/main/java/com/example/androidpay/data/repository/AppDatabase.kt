package com.example.androidpay.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.data.model.BankAccountDao
import com.example.androidpay.data.model.User
import com.example.androidpay.data.model.UserDao


@Database(entities = [User::class, BankAccount::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    public abstract fun userDao(): UserDao?
    public abstract fun bankAccountDao(): BankAccountDao?
}