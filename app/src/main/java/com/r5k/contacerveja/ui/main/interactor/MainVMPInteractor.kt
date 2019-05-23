package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPInteractor
import kotlinx.coroutines.Deferred

interface MainVMPInteractor : MVPInteractor{

    // todo change return
    fun getBillData()

    suspend fun getOpenedBill() : Deferred<List<Bill>>

    fun createBill(bill: Bill) : Boolean

    suspend fun loadDrinksFromBillId(billId : Long) : Deferred<List<Drink>>

    suspend fun createBillAndDefaultDrinks(bill : Bill) : Deferred<DefaultDrinksForBill>

    suspend fun addDrink(drinkName : String) : Deferred<Drink>
}