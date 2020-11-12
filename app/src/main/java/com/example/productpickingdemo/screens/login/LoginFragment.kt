package com.example.productpickingdemo.screens.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel
import com.king.zxing.CaptureActivity
import com.king.zxing.CaptureActivity.KEY_RESULT
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<LoginViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_login
    }

    val code = 777
    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LoginViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

        btnSubmit.setOnClickListener {
            startActivityForResult(Intent(context, CaptureActivity::class.java), code)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 777) {
            val result = data?.getStringExtra(KEY_RESULT)
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}