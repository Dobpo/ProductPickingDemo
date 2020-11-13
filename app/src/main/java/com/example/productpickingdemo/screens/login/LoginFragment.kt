package com.example.productpickingdemo.screens.login

import android.Manifest
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.king.zxing.CaptureActivity
import com.king.zxing.CaptureActivity.KEY_RESULT
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<LoginViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_login
    }

    companion object {
        const val REQUEST_CODE = 777
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LoginViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        btnLogin.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToOrdersFragment())
        }


        ivQrCode.setOnClickListener {
            Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        startActivityForResult(
                            Intent(context, CaptureActivity::class.java),
                            REQUEST_CODE
                        )
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permission: PermissionRequest?,
                        token: PermissionToken?
                    ) {
                        token!!.continuePermissionRequest()
                    }
                }).check()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 777) {
            val result = data?.getStringExtra(KEY_RESULT) ?: "Cancelled"
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}