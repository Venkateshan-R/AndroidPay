package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.Constants
import com.example.androidpay.ui.utils.Constants.PASSWORD_LENGTH
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.data.database.SessionManager
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {

    var resultLiveData: MutableLiveData<ResultData<User>> = MutableLiveData()

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    //user register functionality
    fun registerUser(
        mobileNumber: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        val validationError = validateUserInput(mobileNumber, password, firstName, lastName)
        if (validationError.isEmpty()) {
            viewModelScope.launch {
                val user = userRepositoryImpl.getUserByMobileNumber(mobileNumber);

                if (user == null) {
                    val user = User(mobileNumber, password, firstName, lastName)
                    val userId = userRepositoryImpl.insertUser(user)
                    storeUserId(userId)
                    resultLiveData.value = ResultData.Success(user)

                } else {
                    resultLiveData.value =
                        ResultData.Failure(mApplication.getString(R.string.user_already_exit))
                }
            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }
    }

    //user login functionality
    fun loginUser(
        mobileNumber: String,
        password: String
    ) {

        val validationError = validateUserInput(mobileNumber, password, null, null)
        if (validationError.isEmpty()) {
            viewModelScope.launch {

                val user = userRepositoryImpl.getUserByMobileNumber(mobileNumber);

                if (user != null && user.password.contentEquals(password, ignoreCase = false)) {
                    storeUserId(user.getUserId())
                    resultLiveData.value = ResultData.Success(user)
                } else {
                    resultLiveData.value =
                        ResultData.Failure(mApplication.getString(R.string.user_not_exist))
                }
            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }

    }

    private fun validateUserInput(
        mobileNumber: String,
        password: String,
        firstName: String?,
        lastName: String?
    ): String {

        firstName?.let {
            if (firstName.isEmpty()) {
                return "First name cannot be empty"
            }
        }
        lastName?.let {
            if (lastName.isEmpty()) {
                return "Last name cannot be empty"
            }
        }
        if (mobileNumber.length < Constants.MOBILE_NUMBER_LENGTH) {
            return "Enter Valid ${Constants.MOBILE_NUMBER_LENGTH}  Digit Mobile number"
        }
        if (password.length < PASSWORD_LENGTH) {
            return "Password length must be greater than ${Constants.PASSWORD_LENGTH} "
        }
        return ""
    }

    private fun storeUserId(id: Long) {
        sessionManager.userId = id

    }
}