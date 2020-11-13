package com.example.productpickingdemo.screens.shelf

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.utils.QR_REQUEST_CODE
import com.example.productpickingdemo.utils.injectViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.king.zxing.CaptureActivity
import kotlinx.android.synthetic.main.fragment_shelf.*

class ShelfFragment : BaseFragment<ShelfViewModel>() {

    private var pikedCounterUnit: Int = 0
    private var needCounterUnit: Int = 0
    private lateinit var setCount: String

    private lateinit var order: Order
    private lateinit var product: Product

    override fun layout(): Int {
        return R.layout.fragment_shelf
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): ShelfViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        order = arguments?.let { ShelfFragmentArgs.fromBundle(it).order }!!
        product = arguments?.let { ShelfFragmentArgs.fromBundle(it).product }!!
        btnSubmit.isEnabled = false
        tvProduct.text = product.name
        tvProductBarcode.text = product.barcode

        tvRequiredCount.text = if (product.requestQuantity!! > 1)
            "${product.requestQuantity} units"
        else
            "${product.requestQuantity} unit"

        tvPickedCount.text = if (pikedCounterUnit > 1)
            "$pikedCounterUnit units"
        else
            "$pikedCounterUnit unit"

        needCounterUnit = product.requestQuantity!! - pikedCounterUnit

        tvNeedCount.text = if (needCounterUnit > 1)
            "$needCounterUnit units"
        else
            "$needCounterUnit unit"

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


        btnSubmit.setOnClickListener {
            var productCount: Int? = null

            viewModel.getProducts(order.id).observe(this) {

                productCount = it.size
                Log.d("myLogs", "count  - $productCount")
                val title = if (productCount!! > 1)
                    "End of picking product ${product.name}"
                else
                    "All Products Picked from Order ${order.id}"
                AlertDialog.Builder(requireContext())
                    .setMessage(title)
                    .setNegativeButton("No") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                    .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                        dialog.dismiss()

                        Log.d("myLogs", "product - $productCount")
                        if (productCount!! > 1) {
                            if (product.requestQuantity == pikedCounterUnit)
                                viewModel.deleteProduct(product)

                            navController.navigate(
                                ShelfFragmentDirections.actionShelfFragmentToProductsFragment(
                                    order
                                )
                            )
                        } else {
                            if (product.requestQuantity == pikedCounterUnit)
                                viewModel.deleteProduct(product)

                            navController.navigate(
                                ShelfFragmentDirections.actionShelfFragmentToScanShoppingAreaFragment(
                                    order
                                )
                            )
                        }
                    }
                    .show()
            }
        }

        btnNoMore.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("Really no more?")
                .setNegativeButton("No") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                    navController.navigate(
                        ShelfFragmentDirections.actionShelfFragmentToProductsFragment(
                            order
                        )
                    )
                }
                .show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == QR_REQUEST_CODE) {
            val result = data?.getStringExtra(CaptureActivity.KEY_RESULT)
            if (result != null && result.isNotEmpty()) {
                if (result == product.barcode && needCounterUnit > 0) {
                    setCount = etProductCount.text.toString()
                    if (setCount.isEmpty() || setCount.toInt() < 1) {
                        setCount = "1"
                    }
                    if (setCount.toInt() > needCounterUnit)
                        setCount = needCounterUnit.toString()

                    needCounterUnit -= setCount.toInt()
                    pikedCounterUnit += setCount.toInt()

                    tvNeedCount.text = if (needCounterUnit > 1)
                        "$needCounterUnit units"
                    else
                        "$needCounterUnit unit"

                    tvPickedCount.text = if (pikedCounterUnit > 1)
                        "$pikedCounterUnit units"
                    else
                        "$pikedCounterUnit unit"

                    btnSubmit.isEnabled = needCounterUnit == 0
                } else
                    Toast.makeText(context, "Wrong data!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}