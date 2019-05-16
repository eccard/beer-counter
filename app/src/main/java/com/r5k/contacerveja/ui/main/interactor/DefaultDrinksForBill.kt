package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.drink.Drink

data class DefaultDrinksForBill(val billId: Long,
                                val drinksList : MutableList<Drink>)