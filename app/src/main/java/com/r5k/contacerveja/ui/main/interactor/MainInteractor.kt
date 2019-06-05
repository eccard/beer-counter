package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillsRepository
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.base.BaseInteractor
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class MainInteractor @Inject internal constructor(private val drinksRepoHelper: DrinksRepository,
                                                  private val billsRepository: BillsRepository ): BaseInteractor(),MainVMPInteractor{

    private var mOpenedBillId: Long = -1

    override fun getBillData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        billsRepository.loadBills()
    }


    override suspend fun getOpenedBill() = GlobalScope.async {
        billsRepository.loadOpenedBills()
    }


    override suspend fun loadDrinksFromBillId(billId: Long) = GlobalScope.async {
        mOpenedBillId = billId
        drinksRepoHelper.loadDrinksFromBillId(billId)
    }


    override suspend fun createBillAndDefaultDrinks(bill: Bill) = GlobalScope.async {
        mOpenedBillId = billsRepository.insertBill(bill)

        val drink1Name = "Cerveja Brama"
        val drink2Name = "Vinho"
        val drink3Name = "Cachaça"

        val drink1 = Drink(null, drink1Name, null, 0, mOpenedBillId)
        val drink2 = Drink(null, drink2Name, null, 0, mOpenedBillId)
        val drink3 = Drink(null, drink3Name, null, 0, mOpenedBillId)

        val drink1Id = drinksRepoHelper.insertDrink(drink1)
        val drink2Id = drinksRepoHelper.insertDrink(drink2)
        val drink3Id = drinksRepoHelper.insertDrink(drink3)

        DefaultDrinksForBill(
            mOpenedBillId,
            mutableListOf(
                Drink(drink1Id, drink1Name, null, 0, mOpenedBillId),
                Drink(drink2Id, drink2Name, null, 0, mOpenedBillId),
                Drink(drink3Id, drink3Name, null, 0, mOpenedBillId)
            )
        )
    }

    override fun createBill(bill: Bill): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override suspend fun addDrink(drinkName: String) = GlobalScope.async {
        val drinkId = drinksRepoHelper.insertDrink(Drink(null, drinkName, null, 0, mOpenedBillId))
        Drink(drinkId,drinkName,null,0,mOpenedBillId)
    }


}