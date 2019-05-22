package com.r5k.contacerveja.ui.drink.interactor

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPInteractor
import kotlinx.coroutines.Deferred

interface DrinkMVPInteractor : MVPInteractor {
    suspend fun plusDrink(drink : Drink) : Deferred<Int>
}