package com.r5k.contacerveja.ui.drink.interactor

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPInteractor
import kotlinx.coroutines.Deferred

interface DrinkMVPInteractor : MVPInteractor {
    suspend fun updateDrinkInDb(drink : Drink) : Deferred<Int>
    fun plusQntForDrink(drink : Drink) : Drink
    fun negQntForDrink(drink : Drink) : Drink?
    suspend fun deleteDrink(drink : Drink) : Deferred<Int>
    suspend fun changeDrinkAmount(drink : Drink, amount : Int) : Deferred<Int>
}