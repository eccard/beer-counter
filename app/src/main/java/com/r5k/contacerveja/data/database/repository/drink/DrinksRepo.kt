package com.r5k.contacerveja.data.database.repository.drink

import io.reactivex.Observable

interface DrinksRepo{
    fun isDrinksRepoEmpty(): Observable<Boolean>

    fun insertDrink(drink : Drink): Observable<Boolean>

    fun loadDrinks(): Observable<List<Drink>>
}