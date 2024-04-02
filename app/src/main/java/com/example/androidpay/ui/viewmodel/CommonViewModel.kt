package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.data.repository.TransactionRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommonViewModel(mApplication: Application) : AndroidViewModel(mApplication) {

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }

    var resultLiveData: MutableLiveData<ResultData<User>> = MutableLiveData()
    var logLiveData: MutableLiveData<List<User>> = MutableLiveData()
    var logBankLiveData: MutableLiveData<List<BankAccount>> = MutableLiveData()
    var logTransactionLiveData: MutableLiveData<List<TransactionModel>?> = MutableLiveData()

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl
    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var transactionRepositoryImpl: TransactionRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    fun getallusers(){
        viewModelScope.launch {
            logLiveData.value = userRepositoryImpl.getAllUser()
        }
    }

    fun getAllbankAcc(){
        viewModelScope.launch {
            logBankLiveData.value = bankAccountRepositoryImpl.getAllBankAccount()
        }
    }

    fun getAllTransaction(){
        viewModelScope.launch {
            logTransactionLiveData.value = transactionRepositoryImpl.getAllTransactions()
        }
    }
    fun getUserId() : Long = sessionManager.userId

}