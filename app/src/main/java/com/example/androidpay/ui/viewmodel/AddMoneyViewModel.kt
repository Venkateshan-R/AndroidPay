package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.TransactionRepositoryImpl
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
    val navigationLiveData: MutableLiveData<Int> = MutableLiveData()

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var transactionRepositoryImpl: TransactionRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    fun addAmount(amount: String) {
        val validationError = validateAmount(amount)
        if (validationError.isEmpty()) {
            viewModelScope.launch {
                val bankAccount = bankAccountRepositoryImpl.getBankAccount(getUserId())
                bankAccount?.let {
                    bankAccount.addBalance(amount.toDouble());
                    bankAccountRepositoryImpl.updateBankAccount(bankAccount)
                    saveInTransaction(amount)
                    resultLiveData.value =
                        ResultData.Success(mApplication.getString(R.string.amount_added_successfully))
                    navigationLiveData.value = R.id.action_addMoneyFragment_to_transactionhistoryfragemnt

                } ?: let {
                    resultLiveData.value =
                        ResultData.Failure(mApplication.getString(R.string.please_add_bank_account))
                }
            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }

    }


    fun saveInTransaction(amount: String) {
        viewModelScope.launch {

            val currentBank = bankAccountRepositoryImpl.getBankAccount(getUserId())!!

            val transactionModel = TransactionModel(
                senderId = getUserId(),
                receiverId = getUserId(),
                senderUPIId = currentBank.upiId,
                receiverUPIId = currentBank.upiId,
                amount = amount.toDouble(),
                transactionDate = getTranasactionTime(),
                remarks = "",
                senderName = "",
                receiverName = "",
            )

            transactionRepositoryImpl.saveTransaction(transactionModel)

        }
    }

    fun getTranasactionTime() = System.currentTimeMillis()

    fun getUserId(): Long = sessionManager.userId


    private fun validateAmount(amount: String): String {
        if (amount.isBlank() || amount.toDouble() <= 0)
            return "Enter Amount Greater than 0"

        return ""
    }

}