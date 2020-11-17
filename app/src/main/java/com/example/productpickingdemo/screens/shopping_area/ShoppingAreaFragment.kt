package com.example.productpickingdemo.screens.shopping_area

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.utils.QR_REQUEST_CODE
import com.example.productpickingdemo.utils.ScanUtils
import com.example.productpickingdemo.utils.injectViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.king.zxing.CaptureActivity
import kotlinx.android.synthetic.main.fragment_scan_shopping_area.*

class ShoppingAreaFragment : BaseFragment<ShoppingAreaViewModel>() {
    private lateinit var order: Order

    override fun layout(): Int {
        return R.layout.fragment_scan_shopping_area
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): ShoppingAreaViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        order = arguments?.let { ShoppingAreaFragmentArgs.fromBundle(it).order }!!

        ivScanQr.setOnClickListener {
            Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        startActivityForResult(
                            Intent(context, CaptureActivity::class.java),
                            QR_REQUEST_CODE
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
            context?.let { ScanUtils.scanPositive(it) }
            confirmUnload(order.id.toString())
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private fun confirmUnload(orderId: String) {
        AlertDialog.Builder(requireContext())
            .setMessage("Unload The Products of order $orderId?")
            .setNegativeButton("No") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                viewModel.removeOrder(order)
                navController.navigate(ShoppingAreaFragmentDirections.actionScanShoppingAreaFragmentToOrdersFragment())
            }
            .show()
    }
}