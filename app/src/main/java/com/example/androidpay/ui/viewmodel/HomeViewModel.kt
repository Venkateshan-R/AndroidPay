package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.ui.utils.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {

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


    fun onPayClicked() {
        viewModelScope.launch {
            val userBank = bankAccountRepositoryImpl.getBankAccount(getUserId());
            if (userBank == null)
                resultLiveData.value =
                    ResultData.Failure(mApplication.getString(R.string.please_add_bank_account))
            else
                navigationLiveData.value = R.id.action_homeFragment_to_payFragment
        }
    }

    fun onTransactionClicked() {
        navigationLiveData.value = R.id.action_homeFragment_to_transactionFragment
    }

    fun onSettingsClicked() {
        viewModelScope.launch {
            val userBank = bankAccountRepositoryImpl.getBankAccount(getUserId());
            if (userBank == null)
                resultLiveData.value =
                    ResultData.Failure(mApplication.getString(R.string.please_add_bank_account))
            else
                navigationLiveData.value = R.id.action_homeFragment_to_settingsFragment
        }
    }

    fun onProfileClicked() {
        navigationLiveData.value = R.id.action_homeFragment_to_profileFragment
    }

    fun onAccountClicked() {
        viewModelScope.launch {
            val userBank = bankAccountRepositoryImpl.getBankAccount(getUserId());
            if (userBank == null)
                navigationLiveData.value =
                    R.id.action_homeFragment_to_bankAccountRegistrationFragment
            else
                navigationLiveData.value = R.id.action_homeFragment_to_bankDetailsFragment
        }
    }

    fun onAddMoneyClick() {
        viewModelScope.launch {
            val userBank = bankAccountRepositoryImpl.getBankAccount(getUserId());
            if (userBank == null)
                resultLiveData.value =
                    ResultData.Failure(mApplication.getString(R.string.please_add_bank_account))
            else
                navigationLiveData.value = R.id.action_homeFragment_to_addMoneyFragment
        }
    }

    fun getUserId(): Long = sessionManager.userId

}