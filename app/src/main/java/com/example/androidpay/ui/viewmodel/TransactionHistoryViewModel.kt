package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.data.repository.TransactionRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.showToast
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionHistoryViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {


    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }

    var resultLiveData: MutableLiveData<ResultData<List<TransactionModel>>> = MutableLiveData()

    @Inject
    lateinit var transactionRepositoryImpl: TransactionRepositoryImpl

    @Inject
    lateinit var sessionManager : SessionManager


    fun getAllTransactionHistory(){
        if (getUserId() == 0L) {
            resultLiveData.value = ResultData.Failure(mApplication.getString(R.string.please_login))
            return
        }
        //need to check observerforever
        transactionRepositoryImpl.getAllTransactionsByUserId(getUserId()).observeForever {
            it?.let {
                if (it.size>0)
                resultLiveData.value = ResultData.Success(it)
            }

        }
    }


    fun getUserId(): Long = sessionManager.userId


}