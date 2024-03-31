package com.example.androidpay.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : ViewModel, B : ViewBinding> : AppCompatActivity() {

    lateinit var viewBinding: B
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = getBinding().also {
            setContentView(it.root)
        }

        // Consider using a dependency injection framework for ViewModel
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        initView()
    }

    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }

    abstract fun getBinding(): B

    abstract fun initView()

}