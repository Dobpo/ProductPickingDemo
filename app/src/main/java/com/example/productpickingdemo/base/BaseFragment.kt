package com.example.productpickingdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.productpickingdemo.R
import com.example.productpickingdemo.utils.OnBackPressedListener

abstract class BaseFragment : Fragment(), OnBackPressedListener {
    lateinit var navController: NavController
    lateinit var navOptionsDefaultAnim: NavOptions

    @LayoutRes
    protected abstract fun layout(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = NavHostFragment.findNavController(this)
        navOptionsDefaultAnim = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
            .build()

        return inflater.inflate(layout(), container, false)
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}