package com.r5k.contacerveja.ui.base

import android.util.Log
import java.lang.Exception

abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(protected var interactor: I?) : MVPPresenter<V, I> {

    private val TAG = BasePresenter::class.java.simpleName

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        Log.e(TAG,"onDetach ")
        Log.e(TAG,"onDetach  interactor = null")
        try {
            throw Exception("af")
        } catch (e : Exception){
            Log.e(TAG,"e : Exception")
            Log.e(TAG,Log.getStackTraceString(e))

        }
        view = null
        interactor = null
    }

}