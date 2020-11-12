package com.example.productpickingdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.productpickingdemo.base.BaseActivity
import com.example.productpickingdemo.utils.OnBackPressedListener
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : BaseActivity() {
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragmentNavHost)
        navController.setGraph(R.navigation.root)
        navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment?)!!
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination == null)
            return

        val fragment = navHostFragment.childFragmentManager.primaryNavigationFragment
        if (fragment is OnBackPressedListener && fragment.onBackPressed())
            return

        if (navController.navigateUp())
            return

        finish()
    }
}