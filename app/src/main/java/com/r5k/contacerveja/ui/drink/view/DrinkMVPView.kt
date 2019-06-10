package com.r5k.contacerveja.ui.drink.view

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPView

interface DrinkMVPView : MVPView{
    fun displayTotal(total : String)
    fun onDeletedDrink(drink : Drink)
}