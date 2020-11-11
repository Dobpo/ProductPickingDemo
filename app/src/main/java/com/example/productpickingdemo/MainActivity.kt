package com.example.productpickingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.productpickingdemo.utils.OnBackPressedListener

class MainActivity : AppCompatActivity() {
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