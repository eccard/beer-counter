package com.r5k.contacerveja.ui.main.presenter

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPPresenter
import com.r5k.contacerveja.ui.main.interactor.MainVMPInteractor
import com.r5k.contacerveja.ui.main.view.MainMVPView

interface MainMVPPresenter<V :MainMVPView, I : MainVMPInteractor> : MVPPresenter<V,I>{

    fun onDrawserOptionAboutClick(): Boolean?

    fun createBill()

    fun loadDrinks()

    fun loadDrinksFromBillId(billId : Long)

    fun addNewDrink(drinkName : String)

}