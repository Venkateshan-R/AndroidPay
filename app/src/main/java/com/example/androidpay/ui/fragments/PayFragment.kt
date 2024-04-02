package com.example.androidpay.ui.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidpay.R
import com.example.androidpay.databinding.FragmentPayBinding
import com.example.androidpay.databinding.ItemPopupBinding
import com.example.androidpay.ui.base.BaseFragment
import com.example.androidpay.ui.utils.ResultData
import com.example.androidpay.ui.utils.setOnSafeClickListener
import com.example.androidpay.ui.utils.showToast
import com.example.androidpay.ui.viewmodel.PayViewModel


class PayFragment : BaseFragment<PayViewModel, FragmentPayBinding>() {
    lateinit var alertDialog: AlertDialog
    override fun getBinding(): FragmentPayBinding = FragmentPayBinding.inflate(layoutInflater)
    override fun initView() {
        setUpPopUp()
        setClickListeners()
        setObservers()
    }

    fun setUpPopUp() {

        val binding = ItemPopupBinding.inflate(layoutInflater)
        binding.btnVerfiy.setOnSafeClickListener {
            viewModel.verifyPin(
                binding.etPin.text.toString(),
                viewBinding.etUpi.text.toString(), viewBinding.etAmount.text.toString(),
                viewBinding.etRemarks.text.toString()
            )
        }

       val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)
        alertDialog = builder.create()

    }

    fun setClickListeners() {
        viewBinding.btnPay.setOnSafeClickListener {
            viewModel.transferAmount(
                viewBinding.etUpi.text.toString(), viewBinding.etAmount.text.toString(),
                viewBinding.etRemarks.text.toString()
            )
        }
    }

    fun setObservers() {
        viewModel.resultLiveData.observe(this, Observer {
            when (it) {
                is ResultData.Success -> {
                    context?.showToast("Success")
                    findNavController().popBackStack()
                }

                is ResultData.Failure -> context?.showToast(it.message)
            }
        })
        viewModel.popUpLiveData.observe(this, Observer {
            if (it) {
                showPopUp()
            } else {
                dismissPopup()
            }
        })
    }

    private fun showPopUp() {
        alertDialog.show()
    }

    private fun dismissPopup() {
        alertDialog.dismiss()
    }


}