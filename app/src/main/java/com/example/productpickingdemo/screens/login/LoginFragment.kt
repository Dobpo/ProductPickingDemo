package com.example.productpickingdemo.screens.login

import android.content.Intent
import android.view.View
import android.widget.Toast
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
//            val integrator = IntentIntegrator(activity)
//            integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
//            integrator.setPrompt("Scan a barcode")
//            integrator.setCameraId(0) // Use a specific camera of the device
//            integrator.setOrientationLocked(true)
//            integrator.setBeepEnabled(false)
//            integrator.setBarcodeImageEnabled(true)
//            integrator.initiateScan()
            IntentIntegrator.forSupportFragment(this).initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}