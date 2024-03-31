package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.androidpay.data.repository.LocalRepository
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.SessionManager
import javax.inject.Inject


class CommonViewModel(val myApplication: Application) : AndroidViewModel(myApplication) {

    @Inject
    lateinit var localRepository: LocalRepository


    @Inject
    lateinit var sessionManager: SessionManager

    init {
        (myApplication as MyApplication).appComponent.inject(this)
    }

    override fun onCleared() {
        super.onCleared()
    }

}