package com.r5k.contacerveja.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection

abstract class BaseActivity<T: ViewDataBinding,V: ViewModel> : AppCompatActivity(), BaseFragment.CallBack {

    private var mViewModel: V? = null
    private lateinit var mViewDataBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = mViewModel ?: getViewModel()
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }


    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    abstract fun getBindingVariable(): Int

    private fun performDI() = AndroidInjection.inject(this)

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

}