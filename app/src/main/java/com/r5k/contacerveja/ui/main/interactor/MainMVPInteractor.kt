package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPInteractor
import kotlinx.coroutines.Deferred

interface MainMVPInteractor : MVPInteractor{

    // todo change return
    fun getBillData()

    suspend fun getOpenedBill() : Deferred<List<Bill>>

    fun createBill(bill: Bill) : Boolean

    fun closeBill() : Deferred<Int>

    suspend fun loadDrinksFromBillId(billId : Long) : Deferred<List<Drink>>

    suspend fun createBillAndDefaultDrinks(bill : Bill) : Deferred<DefaultDrinksForBill>

    suspend fun addDrink(drinkName : String) : Deferred<Drink>

    suspend fun loadDrinksFromOpenedBill() : Deferred<List<Drink>>

    suspend fun callTotalOfBill(drinks :List<Drink>) : Deferred<Double>
}