package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.data.database.SessionManager
import javax.inject.Inject

class BankAccRegViewModel (mApplication: Application) : AndroidViewModel(mApplication) {

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }


    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

}