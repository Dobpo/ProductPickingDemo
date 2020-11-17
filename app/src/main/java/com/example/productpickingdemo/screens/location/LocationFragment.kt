package com.example.productpickingdemo.screens.location

import android.Manifest
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Location
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.utils.*
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
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

        val title = "Product ${product.name} in order ${order.orderId}"
        tvTitle.text = title

        viewModel.getLocation(product.locationId!!).observe(viewLifecycleOwner) {
            tvRow.text = it.row
            tvColumn.text = it.column
            tvShelf.text = it.shelf

            location = it
        }

        ivScan.setOnClickListener {
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
            val result = data?.getStringExtra(CaptureActivity.KEY_RESULT)
            result?.let {
                try {
                    val locationItem = Gson().fromJson(result, LocationItem::class.java)
                    switchScanResult(locationItem)
                } catch (ex: Exception) {
                    context?.let { ScanUtils.scanNegative(it) }
                    return
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private fun switchScanResult(locationItem: LocationItem) {
        when (locationItem.type) {
            ROW ->
                if (locationItem.code == location.rowBarcode) {
                    tvRow.setBackgroundResource(R.drawable.border_green)
                    tvRowTitle.setBackgroundResource(R.drawable.border_green)
                    context?.let { ScanUtils.scanCorrectRow(it) }
                } else {
                    tvRow.setBackgroundResource(R.drawable.border_orange)
                    tvRowTitle.setBackgroundResource(R.drawable.border_orange)
                    context?.let { ScanUtils.scanNegative(it) }
                }
            COLUMN ->
                if (locationItem.code == location.columnBarcode) {
                    tvColumn.setBackgroundResource(R.drawable.border_green)
                    tvColumnTitle.setBackgroundResource(R.drawable.border_green)
                    context?.let { ScanUtils.scanCorrectColumn(it) }
                } else {
                    tvColumn.setBackgroundResource(R.drawable.border_orange)
                    tvColumnTitle.setBackgroundResource(R.drawable.border_orange)
                    context?.let { ScanUtils.scanNegative(it) }

                }
            SHELF ->
                if (locationItem.code == location.shelfBarcode) {
                    context?.let { ScanUtils.scanCorrectShelf(it) }
                    navController.navigate(
                        LocationFragmentDirections.actionLocationFragmentToShelfFragment(
                            order,
                            product
                        )
                    )
                } else {
                    tvShelf.setBackgroundResource(R.drawable.border_orange)
                    tvShelfTitle.setBackgroundResource(R.drawable.border_orange)
                    context?.let { ScanUtils.scanNegative(it) }
                }
            else -> Toast.makeText(
                context,
                "Wrong code",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}