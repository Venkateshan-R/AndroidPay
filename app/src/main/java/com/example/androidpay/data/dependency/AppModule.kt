package com.example.androidpay.data.dependency

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.androidpay.data.model.UserDao
import com.example.androidpay.data.repository.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(var application: Application) {

    @Singleton
    @Provides
    fun provideNotesDao(): UserDao =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java, "users_databse"
        ).build().notesDao()

    @Singleton
    @Provides
    fun provideSharedPreference() = application.getSharedPreferences("users",Context.MODE_PRIVATE)

}