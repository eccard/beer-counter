package com.r5k.contacerveja.ui.drink.interactor

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.base.BaseInteractor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class DrinkInteractor @Inject internal
        constructor(private val drinkRepoHelper : DrinksRepository)
    :BaseInteractor(),DrinkMVPInteractor{

    override suspend fun updateDrinkInDb(drink: Drink) = GlobalScope.async {
        drinkRepoHelper.updateDrink(drink)
    }

    override fun plusQntForDrink(drink: Drink): Drink {
        drink.qnt = drink.qnt + 1
        return drink
    }

    override fun negQntForDrink(drink: Drink): Drink {
        drink.qnt = drink.qnt - 1
        return drink
    }
}