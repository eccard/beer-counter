package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillsRepository
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.base.BaseInteractor
import javax.inject.Inject

class MainInteractor @Inject internal constructor(private val drinksRepoHelper: DrinksRepository,
                                                  private val billsRepository: BillsRepository ): BaseInteractor(),MainVMPInteractor{

    private var mBillId : Long = -1

    override fun getBillData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //appDatabase.

//        billsRepository.loadBiils()
    }

    override fun getOpenedBill(): List<Bill> = billsRepository.loadOpenedBills()

    override fun loadDrinksFromBillId(billId: Long): List<Drink>
            = drinksRepoHelper.loadDrinksFromBillId(billId)

//    override fun createBillAndDefaultDrinks(bill: Bill): DefaultDrinksForBill {
//        return billsRepository.insertBiil(bill).flatMap { t ->  createDefaultsDrinks(t)}
//    }

/*
    private fun createDefaultsDrinks(billId : Long): DefaultDrinksForBill {

        this.mBillId = billId

        val drink1Name = "Cerveja Brama"
        val drink2Name = "Vinho"
        val drink3Name = "Cachaça"

        val drink1 = Drink(null,drink1Name,null,0, billId)
        val drink2 = Drink(null,drink2Name,null,0, billId)
        val drink3 = Drink(null,drink3Name,null,0, billId)


        return Single.zip(drinksRepoHelper.insertDrink(drink1),
                            drinksRepoHelper.insertDrink(drink2),
                            drinksRepoHelper.insertDrink(drink3),
                        Function3<Long,Long,Long,DefaultDrinksForBill>
            {
                t1, t2, t3 ->

                DefaultDrinksForBill(billId,
                        mutableListOf(Drink(t1,drink1Name,null,0,billId),
                        Drink(t2,drink2Name,null,0,billId),
                        Drink(t3,drink3Name,null,0,billId)))

            }
        )

    }
*/

    override fun createBill(bill: Bill): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}