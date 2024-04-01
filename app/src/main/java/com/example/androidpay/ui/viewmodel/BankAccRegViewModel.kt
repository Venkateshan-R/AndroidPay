package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.data.model.User
import com.example.androidpay.ui.utils.Constants
import com.example.androidpay.ui.utils.Constants.ACCOUNT_NUMBER_LENGTH
import com.example.androidpay.ui.utils.Constants.AUTHENTICATION_PIN
import com.example.androidpay.ui.utils.ResultData
import kotlinx.coroutines.launch
import javax.inject.Inject

class BankAccRegViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {
    var resultLiveData: MutableLiveData<ResultData<String>> = MutableLiveData()

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }


    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    fun getUserId(): Long = sessionManager.userId

    fun addBankAccount(
        fullname: String,
        accountNo: String,
        IFSCcode: String,
        bankName: String,
        pin: String
    ) {

        val validationError = validateUserInput(fullname, accountNo, IFSCcode, bankName, pin)

        if (validationError.isEmpty()) {
            viewModelScope.launch {

                val accountData =
                    bankAccountRepositoryImpl.getBankAccountByAccNo(accountNo.toLong())
                val userMobileNo =
                    userRepositoryImpl.getUserByuserId(getUserId())?.mobileNumber ?: ""

                if (accountData == null) {
                    val bankAccount = BankAccount(
                        userId = getUserId(),
                        userFullName = fullname,
                        accountNumber = accountNo.toLong(),
                        bankName = bankName,
                        ifscCode = IFSCcode,
                        PIN = pin.toInt(),
                        upiId = generateUpiId(
                            userMobileNo,
                            bankName
                        )
                    )
                    bankAccountRepositoryImpl.insertBankAccount(bankAccount)

                    resultLiveData.value =
                        ResultData.Success(mApplication.getString(R.string.bank_account_added_successfully))


                } else {
                    resultLiveData.value =
                        ResultData.Failure(mApplication.getString(R.string.bank_account_alredy_linked_with_another_user))
                }
            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }

    }

    private fun validateUserInput(
        fullname: String,
        accountNo: String,
        IFSCcode: String,
        bankName: String,
        pin: String

    ): String {

        if (fullname.isEmpty()) {
            return "Full Name cannot be empty"
        }
        if (accountNo.length < Constants.ACCOUNT_NUMBER_LENGTH) {
            return "Enter Valid ${ACCOUNT_NUMBER_LENGTH}  Digit Account number"
        }
        if (IFSCcode.isEmpty()) {
            return "IFSC code cannot be empty"
        }
        if (bankName.isEmpty()) {
            return "Bank name cannot be empty"
        }

        if (pin.length < AUTHENTICATION_PIN) {
            return "Enter Valid ${AUTHENTICATION_PIN} Digit PIN"
        }

        return ""
    }

    fun generateUpiId(mobileNumber: String, bankName: String) = "${mobileNumber}@${bankName}"


}