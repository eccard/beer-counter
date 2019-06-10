package com.r5k.contacerveja.data.database.repository.drink

interface DrinksRepo{
    fun isDrinksRepoEmpty(): Boolean

    fun insertDrink(drinksRepo : Drink): Long

    fun loadDrinks(): List<Drink>

    fun loadDrinksFromBillId(billId : Long) : List<Drink>

    fun updateDrink(drink : Drink) : Int
}