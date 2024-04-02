package com.example.androidpay.data.dependency

import com.example.androidpay.ui.view.MainActivity
import com.example.androidpay.ui.viewmodel.AddMoneyViewModel
import com.example.androidpay.ui.viewmodel.BankAccRegViewModel
import com.example.androidpay.ui.viewmodel.CommonViewModel
import com.example.androidpay.ui.viewmodel.HomeViewModel
import com.example.androidpay.ui.viewmodel.PayViewModel
import com.example.androidpay.ui.viewmodel.SettingsViewModel
import com.example.androidpay.ui.viewmodel.TransactionHistoryViewModel
import com.example.androidpay.ui.viewmodel.UserViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(bankAccRegViewModel: BankAccRegViewModel)
    fun inject(userViewModel: UserViewModel)
    fun inject(commonViewModel: CommonViewModel)
    fun inject(commonViewModel: SettingsViewModel)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(payViewModel: PayViewModel)
    fun inject(addMoneyViewModel: AddMoneyViewModel)
    fun inject(transactionHistoryViewModel: TransactionHistoryViewModel)
}