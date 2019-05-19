package com.r5k.contacerveja.ui.drink.presenter

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPPresenter
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.view.DrinkMVPView

interface DrinkMVPPresenter<V : DrinkMVPView, I : DrinkMVPInteractor> : MVPPresenter<V,I>{
    fun onPlusDrinkSelected(drink : Drink)
}