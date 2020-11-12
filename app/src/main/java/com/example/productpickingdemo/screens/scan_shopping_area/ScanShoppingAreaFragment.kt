package com.example.productpickingdemo.screens.scan_shopping_area

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_scan_shopping_area.*

class ScanShoppingAreaFragment : BaseFragment<ScanShoppingAreaViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_scan_shopping_area
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): ScanShoppingAreaViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        ivScanQr.setOnClickListener {
            Toast.makeText(context, "Is in development", Toast.LENGTH_SHORT).show()
        }
    }
}