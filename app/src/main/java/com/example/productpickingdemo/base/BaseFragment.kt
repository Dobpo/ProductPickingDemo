package com.example.productpickingdemo.base

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel> : Fragment()  {
    protected lateinit var viewModel: V

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        try {
//            (context as BaseActivity<*>).fragmentComponent.inject(this as BaseFragment<BaseViewModel>)
//        } catch (e: UninitializedPropertyAccessException) {
//            (context as BaseActivity<*>).initFragmentComponent()
//            context.fragmentComponent.inject(this as BaseFragment<BaseViewModel>)
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout(), container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialization(view, rootView == null)
        rootView = view
        super.onViewCreated(view, savedInstanceState)
    }
}