package com.r5k.contacerveja.ui.base

interface MVPPresenter<V : MVPView, I :MVPInteractor>{

    fun onAttach(view : V?)

    fun onDetach()

    fun getView(): V?

}