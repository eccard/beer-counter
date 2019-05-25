package com.r5k.contacerveja.data.database.repository.drink

interface DrinksRepo{
    fun isDrinksRepoEmpty(): Boolean

    fun insertDrink(drink : Drink): Long

    fun loadDrinks(): List<Drink>

    fun loadDrinksFromBillId(billId : Long) : List<Drink>

    fun updateDrink(drink : Drink) : Int

    fun deleteDrink(drink : Drink) : Int
}