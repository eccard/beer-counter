package com.r5k.contacerveja.ui.main.view

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPView

interface MainMVPView : MVPView {
    fun lockDrawer()
    fun unlockDrawer()

    fun loadDrinks(drinks: List<Drink>)
}