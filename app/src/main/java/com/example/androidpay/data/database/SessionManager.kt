package com.example.androidpay.data.database

import android.content.SharedPreferences
import com.example.androidpay.data.model.User
import javax.inject.Inject

class SessionManager @Inject constructor() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences



    var userId: Long
        get() {
            return sharedPreferences.getLong("userId", 0)
        }
        set(value) {
            sharedPreferences.edit().putLong("userId", value).apply()
        }

    fun clearAll(){
        sharedPreferences.edit().clear().apply()
    }



}