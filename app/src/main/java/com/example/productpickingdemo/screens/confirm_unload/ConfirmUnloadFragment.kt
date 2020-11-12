package com.example.productpickingdemo.screens.confirm_unload

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_confirm_unload.*

class ConfirmUnloadFragment : BaseFragment<ConfirmUnloadViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_confirm_unload
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): ConfirmUnloadViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        tvMessage.text = "Unload The Products of order AF536?"

        btnSubmit.setOnClickListener {
            Toast.makeText(context, "Is in development", Toast.LENGTH_SHORT).show()
        }
    }
}