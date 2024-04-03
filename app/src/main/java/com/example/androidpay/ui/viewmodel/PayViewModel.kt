package com.example.androidpay.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidpay.R
import com.example.androidpay.data.dao.TransactionDao
import com.example.androidpay.data.database.SessionManager
import com.example.androidpay.data.model.BankAccount
import com.example.androidpay.data.model.TransactionModel
import com.example.androidpay.data.model.User
import com.example.androidpay.data.repository.BankAccountRepositoryImpl
import com.example.androidpay.data.repository.TransactionRepositoryImpl
import com.example.androidpay.data.repository.UserRepositoryImpl
import com.example.androidpay.ui.base.MyApplication
import com.example.androidpay.ui.utils.ResultData
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class PayViewModel(val mApplication: Application) : AndroidViewModel(mApplication) {

    init {
        (mApplication as MyApplication).appComponent.inject(this)
    }

    var resultLiveData: MutableLiveData<ResultData<String>> = MutableLiveData()
    var popUpLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val navigationLiveData: MutableLiveData<Int> = MutableLiveData()


    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var bankAccountRepositoryImpl: BankAccountRepositoryImpl

    @Inject
    lateinit var transactionRepositoryImpl: TransactionRepositoryImpl

    @Inject
    lateinit var sessionManager: SessionManager

    fun transferAmount(receiverUpi: String, amount: String, remarks: String) {

        if (getUserId() == 0L) {
            resultLiveData.value = ResultData.Failure(mApplication.getString(R.string.please_login))
            return
        }

        val validationError = validateInputs(receiverUpi, amount)

        if (validationError.isEmpty()) {
            viewModelScope.launch {

                val currentUserBankAccount = bankAccountRepositoryImpl.getBankAccount(getUserId())
                val receiverBankAccount =
                    bankAccountRepositoryImpl.getBankAccountByUpiId(receiverUpi)

                currentUserBankAccount?.let { currentBank ->
                    receiverBankAccount?.let { receiverBank ->


                        if (currentBank.upiId.contentEquals(receiverBank.upiId)) {
                            resultLiveData.value =
                                ResultData.Failure(mApplication.getString(R.string.amount_cannot_be_transferred_to_same_account))

                        } else if (currentBank.balance < amount.toDouble()) {
                            resultLiveData.value =
                                ResultData.Failure((mApplication.getString(R.string.insufficient_balance)))

                        } else if (amount.toDouble() > currentBank.perTransactionLimit) {
                            resultLiveData.value =
                                ResultData.Failure(mApplication.getString(R.string.you_are_exceeding_per_transaction_limit))

                        } else if (amount.toDouble() > receiverBank.perTransactionLimit) {
                            resultLiveData.value =
                                ResultData.Failure(mApplication.getString(R.string.transfer_failed_amount_exceeds_receiver_s_limit))
                        } else if ((getTodayTransaction(currentBank) + amount.toDouble()) > currentBank.perDayTransactionLimit) {
                            resultLiveData.value =
                                ResultData.Failure(mApplication.getString(R.string.unable_to_transfer_your_daily_transfer_limit_has_been_reached))
                        } else if ((getTodayTransaction(receiverBankAccount) + amount.toDouble()) > receiverBankAccount.perDayTransactionLimit) {
                            resultLiveData.value =
                                ResultData.Failure(mApplication.getString(R.string.unable_to_transfer_receiver_s_daily_transfer_limit_has_been_reached))
                        } else {
                            popUpLiveData.value = true
                        }


                    } ?: let {
                        resultLiveData.value =
                            ResultData.Failure((mApplication.getString(R.string.please_enter_valid_upi)))
                    }

                } ?: let {
                    resultLiveData.value =
                        ResultData.Failure(mApplication.getString(R.string.please_add_bank_account))
                }


            }
        } else {
            resultLiveData.value = ResultData.Failure(validationError)
        }


    }

    fun verifyPin(pin: String, receiverUpi: String, amount: String, remarks: String) {
        viewModelScope.launch {

            val currentBank = bankAccountRepositoryImpl.getBankAccount(getUserId())!!
            val receiverBank =
                bankAccountRepositoryImpl.getBankAccountByUpiId(receiverUpi)!!

            if (pin.isBlank() || pin.toInt() != currentBank.PIN) {
                resultLiveData.value =
                    ResultData.Failure(mApplication.getString(R.string.invalid_pin))
                return@launch
            }

            currentBank.deductBalance(amount.toDouble())
            receiverBank.addBalance(amount.toDouble())


            val transactionModel = TransactionModel(
                senderId = getUserId(),
                receiverId = receiverBank.userId,
                senderUPIId = currentBank.upiId,
                receiverUPIId = receiverBank.upiId,
                amount = amount.toDouble(),
                transactionDate = getTranasactionTime(),
                remarks = remarks,
                receiverName = receiverBank.userFullName,
                senderName = currentBank.userFullName
            )

            transactionRepositoryImpl.saveTransaction(transactionModel)
            bankAccountRepositoryImpl.updateBankAccount(currentBank)
            bankAccountRepositoryImpl.updateBankAccount(receiverBank)

            popUpLiveData.value = false
            resultLiveData.value =
                ResultData.Success(mApplication.getString(R.string.amount_transfered_successfully))
            navigationLiveData.value = R.id.action_payFragment_to_transactionhistoryfragemnt


        }

    }


    fun getUserId(): Long = sessionManager.userId

    fun getTranasactionTime() = System.currentTimeMillis()


    private fun validateInputs(receiverUpi: String, amount: String): String {
        if (receiverUpi.isBlank() || !receiverUpi.contains("@"))
            return mApplication.getString(R.string.please_enter_valid_upi)
        if (amount.isBlank() || amount.toDouble() <= 0)
            return "Enter Amount Greater than 0"
        return ""
    }

    suspend fun getTodayTransaction(bankAccount: BankAccount): Double {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0) // Set hour to 0
        calendar.set(Calendar.MINUTE, 0) // Set minute to 0
        calendar.set(Calendar.SECOND, 0) // Set second to 0
        calendar.set(Calendar.MILLISECOND, 0) // Set millisecond to 0
        val dayStart = calendar.timeInMillis // Get timestamp for start of day

        calendar.add(Calendar.DAY_OF_MONTH, 1) // Add 1 day
        calendar.set(Calendar.HOUR_OF_DAY, 0) // Set hour to 0 again (redundant but explicit)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val dayEnd = calendar.timeInMillis // Get timestamp for end of day

        return transactionRepositoryImpl.getTodaysTransactions(dayStart, dayEnd, bankAccount.upiId)
            .sumOf { it.amount }

    }


}