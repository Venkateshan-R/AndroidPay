package com.example.androidpay.ui.utils

sealed class ResultData<T> {
  data class Success<T>(val data: T) : ResultData<T>()
  data class Failure<T>(val message: String) : ResultData<T>()
}
sealed class DataHolder<T> {
  data class Datas<T>(val tag: T,val value : String) : DataHolder<T>()

}
