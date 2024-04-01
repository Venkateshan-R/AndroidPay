package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.ResultData
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddMoneyViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }

    var resultLiveData: MutableLiveData<ResultData<String>> = MutableLiveData()

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    fun addAmount(amount: String ){
        val validationError = validateAmount(amount)
        if (validationError.isEmpty()) {
            viewModelScope.launch {
                val bankAccount = bankAccountRepositoryImpl.getBankAccount(getUserId())
                    bankAccount?.let {
                        bankAccount.balance+=amount.toDouble();
                        bankAccountRepositoryImpl.updateBankAccount(bankAccount)

                        resultLiveData.value = ResultData.Success(mApplication.getString(R.string.amount_added_successfully))

                    } ?:let {resultLiveData.value = ResultData.Failure(mApplication.getString(R.string.please_add_bank_account)) }


            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }



    }
    fun getUserId(): Long = sessionManager.userId


    private fun validateAmount(amount : String): String {
        if (amount.isBlank() || amount.toInt() <= 0)
            return "Enter Amount Greater than 0"

        return ""
    }

}