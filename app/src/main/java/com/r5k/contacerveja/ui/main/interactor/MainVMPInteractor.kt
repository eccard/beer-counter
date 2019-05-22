package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPInteractor

interface MainVMPInteractor : MVPInteractor{

    // todo change return
    fun getBillData()

    fun getOpenedBill() : List<Bill>

    fun createBill(bill: Bill) : Boolean

    fun loadDrinksFromBillId(billId : Long) : List<Drink>

//    fun createBillAndDefaultDrinks(bill : Bill) :DefaultDrinksForBill
}