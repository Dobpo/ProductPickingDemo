package com.example.productpickingdemo.screens.location

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
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

    private lateinit var order: Order
    private lateinit var product: Product
    private lateinit var location: Location

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LocationViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        order = arguments?.let { LocationFragmentArgs.fromBundle(it).order }!!
        product = arguments?.let { LocationFragmentArgs.fromBundle(it).product }!!

        val title = "Product ${product.name} in order ${order.number}"
        tvTitle.text = title

        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer {
            tvValueRow.text = it.row
            tvValueColumn.text = it.column
            tvValueShelf.text = it.shelf

            location = it
        })

        viewModel.getLocation(product.id)

        ivScan.setOnClickListener {
            startActivityForResult(
                Intent(context, CaptureActivity::class.java),
                QR_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == QR_REQUEST_CODE) {
            val result = data?.getStringExtra(CaptureActivity.KEY_RESULT)
            if (result != null) {
                tvWrong.visibility = View.GONE
                switchScanResult(result)
            } else {
                tvWrong.visibility = View.VISIBLE
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private fun switchScanResult(barcode: String) {
        when (barcode) {
            location.columnBarcode -> Toast.makeText(
                context,
                "Correct column",
                Toast.LENGTH_SHORT
            ).show()
            location.rowBarcode -> Toast.makeText(
                context,
                "Correct row",
                Toast.LENGTH_SHORT
            ).show()
            location.shelfBarcode -> navController.navigate(
                LocationFragmentDirections.actionLocationFragmentToShelfFragment(
                    order,
                    product
                )
            )
            else -> Toast.makeText(
                context,
                "Wrong location",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}