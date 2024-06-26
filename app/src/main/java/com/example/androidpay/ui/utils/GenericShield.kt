package com.example.androidpay.ui.utils

sealed class ResultData<T> {
  data class Success<T>(val data: T,val message: String="") : ResultData<T>()
  data class Failure<T>(val message: String) : ResultData<T>()
}
