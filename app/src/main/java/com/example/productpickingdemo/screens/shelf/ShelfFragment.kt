package com.example.productpickingdemo.screens.shelf

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.utils.Modes
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

        val listMode =
            listOf("Scan only", "Enter quantity first", "Scan first then quantity")

        val adapter = ArrayAdapter(
            baseContext,
            android.R.layout.simple_spinner_item,
            listMode
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.run {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    checkMode(position)
                    viewModel.setMode(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

        etProductCount.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus)
                view.hideKeyboard()
        }

        spinner.setSelection(viewModel.getMode())
        checkMode(viewModel.getMode())

        setText(tvRequiredCount, product.requestQuantity!!)
        setText(tvPickedCount, pikedCounterUnit)

        needCounterUnit = product.requestQuantity!! - pikedCounterUnit
        setText(tvNeedCount, needCounterUnit)

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

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == QR_REQUEST_CODE) {
            val result = data?.getStringExtra(CaptureActivity.KEY_RESULT)
            if (result != null && result.isNotEmpty()) {
                if (result == product.barcode && needCounterUnit > 0) {
                    setCount = etProductCount.text.toString()
                    when (viewModel.getMode()) {
                        Modes.SCAN_ONLY.ordinal -> {
                            setCount = "1"
                            changeQuantity()
                        }

                        Modes.ENTER_QUANTITY_FIRST.ordinal -> {
                            if (setCount.isEmpty() || setCount.toInt() < 1) {
                                setCount = "1"
                            }
                            if (setCount.toInt() > needCounterUnit)
                                setCount = needCounterUnit.toString()

                            changeQuantity()
                        }

                        Modes.ENTER_QUANTITY_LAST.ordinal -> {
                            showDialog()
                        }
                    }
                } else
                    Toast.makeText(context, "Wrong data!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private fun setText(view: TextView, count: Int) {
        view.text = if (count > 1)
            "$count units"
        else
            "$count unit"
    }

    private fun checkMode(mode: Int) {
        when (mode) {
            Modes.SCAN_ONLY.ordinal -> {
                tilProductCount.visibility = View.GONE
            }

            Modes.ENTER_QUANTITY_FIRST.ordinal -> {
                tilProductCount.visibility = View.VISIBLE
            }

            Modes.ENTER_QUANTITY_LAST.ordinal -> {
                tilProductCount.visibility = View.GONE
            }
        }
    }

    private fun showDialog() {
        val maxLength = 10
        val fArray = arrayOfNulls<InputFilter>(1)
        fArray[0] = LengthFilter(maxLength)

        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(48, 0, 32, 0)

        val input = EditText(requireContext())
        input.layoutParams = params
        input.inputType = InputType.TYPE_CLASS_NUMBER
        input.setLines(1)
        input.maxLines = 1
        input.filters = fArray
        layout.addView(input, params)

        AlertDialog.Builder(requireContext())
            .setMessage("Enter quantity?")
            .setView(layout)
            .setCancelable(false)
            .setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                setCount = "1"
                dialog.dismiss()
                changeQuantity()
            }
            .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                setCount = input.text.toString()

                if (setCount.toInt() > needCounterUnit)
                    setCount = needCounterUnit.toString()

                dialog.dismiss()
                changeQuantity()
            }
            .show()
    }

    private fun changeQuantity() {
        needCounterUnit -= setCount.toInt()
        pikedCounterUnit += setCount.toInt()

        setText(tvNeedCount, needCounterUnit)
        setText(tvPickedCount, pikedCounterUnit)

        btnSubmit.isEnabled = needCounterUnit == 0
    }
}