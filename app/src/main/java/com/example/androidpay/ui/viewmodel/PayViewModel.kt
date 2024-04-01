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

class PayViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {

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

    fun transferAmount(receiverUpi: String, amount: String, remarks: String) {

        if (getUserId() == 0L) {
            resultLiveData.value = ResultData.Failure(mApplication.getString(R.string.please_login))
            return
        }

        val validationError = validateInputs(receiverUpi, amount)

        if (validationError.isEmpty()) {
            viewModelScope.launch {

                val currentUserBankAccount = bankAccountRepositoryImpl.getBankAccount(getUserId())
                val receiverBankAccount =
                    bankAccountRepositoryImpl.getBankAccountByUpiId(receiverUpi)

                currentUserBankAccount?.let { currentBank ->
                    receiverBankAccount?.let { receiverBank ->


                        if (currentBank.upiId.contentEquals(receiverBank.upiId)) {
                            resultLiveData.value =
                                ResultData.Failure(mApplication.getString(R.string.amount_cannot_be_transferred_to_same_account))

                        } else if (currentBank.balance < amount.toDouble()) {
                            resultLiveData.value =
                                ResultData.Failure((mApplication.getString(R.string.insufficient_balance)))

                        } else {
                            currentBank.balance -= amount.toDouble()
                            receiverBank.balance += amount.toDouble()

                            bankAccountRepositoryImpl.updateBankAccount(currentBank)
                            bankAccountRepositoryImpl.updateBankAccount(receiverBankAccount)
                            resultLiveData.value =
                                ResultData.Success(mApplication.getString(R.string.amount_transfered_successfully))


                        }


                    } ?: let {
                        resultLiveData.value =
                            ResultData.Failure((mApplication.getString(R.string.you_have_entered_invalid_upi)))
                    }

                } ?: let {
                    resultLiveData.value =
                        ResultData.Failure(mApplication.getString(R.string.please_add_bank_account))
                }


            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }


    }

    fun getUserId(): Long = sessionManager.userId


    private fun validateInputs(receiverUpi: String, amount: String): String {
        if (receiverUpi.isBlank() || !receiverUpi.contains("@"))
            return "Please Enter Valid UPI"
        if (amount.isBlank() || amount.toDouble() <= 0)
            return "Enter Amount Greater than 0"
        return ""
    }


}