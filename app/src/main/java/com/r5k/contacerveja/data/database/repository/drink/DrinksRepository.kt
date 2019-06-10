package com.r5k.contacerveja.data.database.repository.drink

import javax.inject.Inject

class DrinksRepository @Inject constructor(private val drinksDao: DrinksDao) :DrinksRepo{

    override fun isDrinksRepoEmpty(): Boolean = drinksDao.loadAll().isEmpty()

    override fun insertDrink(drinks: Drink): Long  {
        return drinksDao.insert(drinks)
    }

    override fun loadDrinks(): List<Drink> =  drinksDao.loadAll()


    override fun loadDrinksFromBillId(billId: Long): List<Drink> =
         drinksDao.loadDrinksFromBillId(billId)

    override fun updateDrink(drink: Drink): Int {
        return drinksDao.updateDrink(drink)
    }

    override fun deleteDrink(drink: Drink) : Int{
        return drinksDao.deleteDrink(drink)
    }
}