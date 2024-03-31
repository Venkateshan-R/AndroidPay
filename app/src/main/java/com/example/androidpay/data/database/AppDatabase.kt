package com.example.androidpay.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.data.dao.BankAccountDao
import com.example.androidpay.data.dao.TransactionDao
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.data.model.User
import com.example.androidpay.data.dao.UserDao


@Database(entities = [User::class, BankAccount::class,TransactionModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    public abstract fun userDao(): UserDao
    public abstract fun bankAccountDao(): BankAccountDao

    public abstract fun transactionDao(): TransactionDao
}