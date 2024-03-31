package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val mobileNumber: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    @PrimaryKey(autoGenerate = true)
    private val userId: Long=0
) {
    fun getUserId(): Long {
        return userId
    }
}