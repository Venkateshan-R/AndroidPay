package com.example.androidpay.data.dependency

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.androidpay.data.interfaces.BankAccountRepository
import com.example.androidpay.data.interfaces.UserRepository
import com.example.androidpay.data.model.BankAccountDao
import com.example.androidpay.data.model.UserDao
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.MyDatabase
import com.example.androidpay.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(var application: Application) {

    @Singleton
    @Provides
    fun provideDatabase(): MyDatabase =
        Room.databaseBuilder(
            application,
            MyDatabase::class.java, "app_database"
        ).build();

    @Singleton
    @Provides
    fun provideUserDao(myDatabase: MyDatabase) = myDatabase.userDao();

    @Singleton
    @Provides
    fun provideBankAccountDao(myDatabase: MyDatabase) = myDatabase.bankAccountDao();

    @Singleton
    @Provides
    fun provideSharedPreference() = application.getSharedPreferences("users",Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideBankAccountRepository(bankAccountDao: BankAccountDao): BankAccountRepository = BankAccountRepositoryImpl(bankAccountDao)

}