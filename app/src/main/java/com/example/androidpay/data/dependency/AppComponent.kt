package com.example.androidpay.data.dependency

import com.example.androidpay.ui.view.MainActivity
import com.example.androidpay.ui.viewmodel.CommonViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(commonViewModel: CommonViewModel)
}