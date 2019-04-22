package com.r5k.contacerveja.ui.main.presenter

import com.r5k.contacerveja.ui.base.MVPPresenter
import com.r5k.contacerveja.ui.main.interactor.MainVMPInteractor
import com.r5k.contacerveja.ui.main.view.MainMVPView

interface MainMVPPresenter<V :MainMVPView, I : MainVMPInteractor> : MVPPresenter<V,I>{

    fun onDrawserOptionAboutClick(): Boolean?

    fun createBill()

    fun loadDrinksFromBillId(billId : Long)

}