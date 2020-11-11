package com.example.productpickingdemo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.utils.OnBackPressedListener
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel> : Fragment(), OnBackPressedListener {
    protected lateinit var viewModel: V
    protected lateinit var navController: NavController
    protected lateinit var navOptionsDefaultAnim: NavOptions

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    protected lateinit var baseContext: Context

    @Inject
    lateinit var baseActivity: BaseActivity<*>


    protected var rootView: View? = null
    var isVisible: (fragment: Fragment) -> Boolean = { true }

    @LayoutRes
    protected abstract fun layout(): Int
    protected abstract fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): V
    protected abstract fun initialization(view: View, isFirstInit: Boolean)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel(viewModelFactory)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialization(view, rootView == null)
        rootView = view
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}