package com.example.androidpay.ui.base

import android.app.Application
import com.example.androidpay.data.dependency.AppComponent
import com.example.androidpay.data.dependency.AppModule
import com.example.androidpay.data.dependency.DaggerAppComponent

class MyApplication : Application(){
lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}