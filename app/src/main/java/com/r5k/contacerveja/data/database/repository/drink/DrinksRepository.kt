package com.r5k.contacerveja.data.database.repository.drink

import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DrinksRepository @Inject constructor(private val drinksDao: DrinksDao) :DrinksRepo{

    override fun isDrinksRepoEmpty(): Observable<Boolean> = Observable.just(drinksDao.loadAll().isEmpty())

    override fun insertDrink(drinks: Drink): Single<Boolean> {
        return Single.just(this.drinksDao.insert(drinks) != -1L)
    }

    override fun loadDrinks(): Observable<List<Drink>> = Observable.fromCallable { drinksDao.loadAll() }


    override fun loadDrinksFromBillId(billId: Long): Single<List<Drink>> =
        Single.fromCallable { drinksDao.loadDrinksFromBillId(billId) }

}