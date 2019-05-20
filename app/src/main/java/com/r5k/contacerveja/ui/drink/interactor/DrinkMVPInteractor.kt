package com.r5k.contacerveja.ui.drink.interactor

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPInteractor
import io.reactivex.Single

interface DrinkMVPInteractor : MVPInteractor {
    fun plusDrink(drink : Drink) : Single<Int>
}