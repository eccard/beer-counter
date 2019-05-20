package com.r5k.contacerveja.ui.drink.interactor

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.base.BaseInteractor
import javax.inject.Inject

class DrinkInteractor @Inject internal
        constructor(private val drinkRepoHelper : DrinksRepository)
    :BaseInteractor(),DrinkMVPInteractor{

    override fun plusDrink(drink: Drink) = drinkRepoHelper.updateDrink(drink)

}