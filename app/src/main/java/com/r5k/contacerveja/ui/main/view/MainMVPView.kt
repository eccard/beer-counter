package com.r5k.contacerveja.ui.main.view

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPView
import com.r5k.contacerveja.ui.main.interactor.DefaultDrinksForBill

interface MainMVPView : MVPView {
    fun lockDrawer()
    fun unlockDrawer()

    fun loadDefaultDrinks(drinksForBill : DefaultDrinksForBill)
    fun loadDrinksForOpenedBill(drinks: List<Drink>)

    fun addNewDrink(drink:Drink)

    fun showTotal(drinks: List<Drink>, total : Double)

    fun onClosedBill()

    fun showPlusButton()
}