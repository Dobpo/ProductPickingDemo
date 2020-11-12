package com.example.productpickingdemo.screens.confirm_picked

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_confirm_picked.*

class ConfirmPickedFragment : BaseFragment<ConfirmPickedViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_confirm_picked
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): ConfirmPickedViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        tvMessage.text = "All Products Picked from Order AF536"

        btnSubmit.setOnClickListener {
            Toast.makeText(context, "Is in developing", Toast.LENGTH_SHORT).show()
        }
    }
}