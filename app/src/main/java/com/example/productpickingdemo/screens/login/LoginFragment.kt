package com.example.productpickingdemo.screens.login

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<LoginViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_login
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LoginViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

        btnSubmit.setOnClickListener {
            val integrator = IntentIntegrator(activity)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
            integrator.setPrompt("Scan a barcode")
            integrator.setCameraId(0) // Use a specific camera of the device
            integrator.setOrientationLocked(true)
            integrator.setBeepEnabled(false)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }
    }
}