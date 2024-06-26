package com.example.androidpay.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.androidpay.R
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : ViewModel, B : ViewBinding> : Fragment() {

    protected lateinit var viewBinding: B
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(getViewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (!this::viewBinding.isInitialized) {
            viewBinding = getBinding()
            initView()
        }
        return viewBinding.root
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getViewModelClass(): Class<VM> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
    }

    abstract fun getBinding(): B

    abstract fun initView()
}