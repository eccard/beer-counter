package com.r5k.contacerveja.data.database.repository.drink

import io.reactivex.Observable
import io.reactivex.Single

interface DrinksRepo{
    fun isDrinksRepoEmpty(): Observable<Boolean>

    fun insertDrink(drink : Drink): Single<Boolean>

    fun loadDrinks(): Observable<List<Drink>>

    fun loadDrinksFromBillId(billId : Long) : Single<List<Drink>>
}