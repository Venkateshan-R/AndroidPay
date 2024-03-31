package com.example.androidpay.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Users")
class UsersModel :Serializable{

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo("name")
    var name = ""

    @ColumnInfo("mobileNumber")
    var mobileNumber = ""

    @ColumnInfo("time")
    var time :Int = 0;



}