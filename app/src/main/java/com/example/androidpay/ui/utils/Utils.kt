package com.example.androidpay.ui.utils

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.Group

fun Context.showToast(msg : String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun View.setOnSafeClickListener(interval: Int = 1000, onSafeClick: (View) -> Unit) {
    var lastClickTime = SystemClock.elapsedRealtime()
    setOnClickListener {
        val currentClickTime = SystemClock.elapsedRealtime()
        if (currentClickTime - lastClickTime < interval) return@setOnClickListener
        lastClickTime = currentClickTime
        onSafeClick(it)
    }
}

/*fun Group.setAllOnClickListener(listener: (View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnSafeClickListener(onSafeClick =listener)
    }
}*/

fun View.visible() { visibility = View.VISIBLE }
fun View.gone() { visibility = View.GONE }

fun View.setVisible(isVisible: Boolean) {
    visibility = when (isVisible) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}

