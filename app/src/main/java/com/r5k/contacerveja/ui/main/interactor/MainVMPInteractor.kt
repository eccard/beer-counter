package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.MVPInteractor
import io.reactivex.Single

interface MainVMPInteractor : MVPInteractor{

    // todo change return
    fun getBillData()

    fun getOpenedBill() : Single<List<Bill>>

    fun createBill(bill: Bill) : Single<Boolean>

    fun loadDrinksFromBillId(billId : Long) : Single<List<Drink>>

    fun createBillAndDefaultDrinks(bill : Bill) :Single<DefaulDrinksForBill>
}