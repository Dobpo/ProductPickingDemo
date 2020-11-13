package com.example.productpickingdemo.screens.location

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Location
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.utils.QR_REQUEST_CODE
import com.example.productpickingdemo.utils.injectViewModel
import com.king.zxing.CaptureActivity
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : BaseFragment<LocationViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_location
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LocationViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        val order: Order? = arguments?.let { LocationFragmentArgs.fromBundle(it).order }
        val product: Product? = arguments?.let { LocationFragmentArgs.fromBundle(it).product }
        val location = Location(1, "R4", "xxxxxxxxxxxxx", "T7", "1268392163", "9", "0000000000")
        tvProduct.text = product?.name
        tvOrder.text = "in order ${order?.id}"

        tvValueRow.text = location.row
        tvValueColumn.text = location.column
        tvValueShelf.text = location.shelf
        ivScan.setOnClickListener {
            startActivityForResult(
                Intent(context, CaptureActivity::class.java),
                QR_REQUEST_CODE
            )
        }

        ivScan.setOnLongClickListener {
            navController.navigate(LocationFragmentDirections.actionLocationFragmentToShelfFragment())
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == QR_REQUEST_CODE) {
            val result = data?.getStringExtra(CaptureActivity.KEY_RESULT)
            if (result != null) {
                tvWrong.visibility = View.GONE
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            } else {
                tvWrong.visibility = View.VISIBLE
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}