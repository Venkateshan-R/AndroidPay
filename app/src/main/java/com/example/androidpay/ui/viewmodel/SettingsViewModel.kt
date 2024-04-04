package com.example.androidpay.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.ui.utils.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }

    var resultLiveData: MutableLiveData<ResultData<BankAccount>> = MutableLiveData()

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    val navigationLiveData: MutableLiveData<Int?> = MutableLiveData()


    fun getUserBankAccount() {
        viewModelScope.launch {
            val userBank = bankAccountRepositoryImpl.getBankAccount(getUserId());
            if (userBank == null) {

            } else
                resultLiveData.value = ResultData.Success(userBank!!)
        }
    }

    fun onSaveclick(perTxnLimit: String, dailyTxnLimit: String) {


        val validationError = validateInputs(perTxnLimit, dailyTxnLimit)

        if (validationError.isEmpty()) {
            viewModelScope.launch {
                val userBank = bankAccountRepositoryImpl.getBankAccount(getUserId());
                userBank?.let {
                    userBank.perTransactionLimit = perTxnLimit.toDouble()
                    userBank.perDayTransactionLimit = dailyTxnLimit.toDouble()

                    bankAccountRepositoryImpl.updateBankAccount(userBank)

                    navigationLiveData.value = null

                }

            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }

    }

    private fun validateInputs(perTxnLimit: String, dailyTxnLimit: String): String {

        if (perTxnLimit.isBlank() || perTxnLimit.toDouble() <= 0)
            return mApplication.getString(R.string.per_transaction_limit_must_be_greater_than_0)
        if (dailyTxnLimit.isBlank() || dailyTxnLimit.toDouble() <= 0)
            return mApplication.getString(R.string.daily_transaction_limit_must_be_greater_than_0)
        return ""
    }


    fun getUserId(): Long = sessionManager.userId

    fun logout() {
        sessionManager.clearAll()
    }

    fun isUserLoggedIn(): Boolean = getUserId() > 0


}