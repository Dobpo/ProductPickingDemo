package com.example.productpickingdemo.screens.login

import android.Manifest
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.User
import com.example.productpickingdemo.utils.QR_REQUEST_CODE
import com.example.productpickingdemo.utils.injectViewModel
import com.google.gson.Gson
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

    private lateinit var users: List<User>

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LoginViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        //viewModel.initDatabase()

        viewModel.getUsers().observe(viewLifecycleOwner) {
            users = it
        }

        btnLogin.setOnClickListener {
            checkUser(etLogin.text.toString(), etPassword.text.toString())
        }

        ivQrCode.setOnClickListener {
            Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        startActivityForResult(
                            Intent(context, CaptureActivity::class.java), QR_REQUEST_CODE
                        )
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        Toast.makeText(
                            context,
                            getString(R.string.permission_denied),
                            Toast.LENGTH_SHORT
                        ).show()
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
        if (requestCode == QR_REQUEST_CODE) {
            val result = data?.getStringExtra(KEY_RESULT)
            if (result != null && result.isNotEmpty()) {
                try {
                    val user = Gson().fromJson(result, User::class.java)
                    checkUser(user)
                } catch (e: Exception) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    return
                }
            } else {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private fun checkUser(user: User) {
        users.forEach {
            if (it.id == user.id && it.password == user.password) {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToOrdersFragment())
                return
            }
        }
        Toast.makeText(context, "No such user", Toast.LENGTH_SHORT).show()
    }

    private fun checkUser(id: String, password: String) {
        users.forEach {
            if (it.id.toString() == id && it.password == password) {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToOrdersFragment())
                return
            }
        }
        Toast.makeText(context, "No such user", Toast.LENGTH_SHORT).show()
    }
}