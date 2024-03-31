package com.example.androidpay.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val mobileNumber: Long,
    val passwordHash: String // Hashed password instead of plain text
)