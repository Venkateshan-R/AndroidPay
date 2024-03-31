package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidpay.R
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.data.database.SessionManager
import javax.inject.Inject

class SettingsViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }

    var resultLiveData: MutableLiveData<ResultData<User>> = MutableLiveData()
    var logLiveData: MutableLiveData<List<User>> = MutableLiveData()

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    val btnText: MutableLiveData<String> = MutableLiveData()
    val navigationLiveData: MutableLiveData<Int> = MutableLiveData()

    fun getButtonText(): LiveData<String> {
        return btnText.also {
            it.value = if (isUserLoggedIn()) mApplication.getString(R.string.logout)
            else mApplication.getString(R.string.login)
        }
    }

    fun onLoginButtonclick() {
        if (isUserLoggedIn()) {
            logout()
            btnText.value =mApplication.getString(R.string.login)
        } else navigationLiveData.value = R.id.action_settingsFragment_to_loginFragment


    }

    fun onAddAccountButtonclick() {
        if (isUserLoggedIn()) {
            navigationLiveData.value = R.id.action_settingsFragment_to_loginFragment
        }


    }

    fun getUserId(): Long = sessionManager.userId

    fun logout() {
        sessionManager.clearAll()
    }

    fun isUserLoggedIn(): Boolean = getUserId() > 0


}