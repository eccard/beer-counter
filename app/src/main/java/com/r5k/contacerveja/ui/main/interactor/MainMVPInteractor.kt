package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillsRepository
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.base.BaseInteractor
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class MainMVPInteractor @Inject internal constructor(private val drinksRepoHelper: DrinksRepository,
                                                     private val billsRepository: BillsRepository ): BaseInteractor(),MainVMPInteractor{

    private var mBillId : Long = -1

    override fun getBillData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //appDatabase.

//        billsRepository.loadBiils()
    }

    override fun getOpenedBill(): Single<List<Bill>> = billsRepository.loadOpenedBills()

    override fun loadDrinksFromBillId(billId: Long): Single<List<Drink>>
            = drinksRepoHelper.loadDrinksFromBillId(billId)

    override fun createBillAndDefaultDrinks(bill: Bill): Single<DefaultDrinksForBill> {
        return billsRepository.insertBiil(bill).flatMap { t ->  createDefaultsDrinks(t)}
    }

    fun createDefaultsDrinks(billId : Long): Single<DefaultDrinksForBill> {

        this.mBillId = billId

        val drink1 = Drink(null,"Cerveja Brama",null,null, billId)
        val drink2 = Drink(null,"Vinho",null,null, billId)
        val drink3 = Drink(null,"Cachaça",null,null, billId)


        return Single.zip(drinksRepoHelper.insertDrink(drink1),
                            drinksRepoHelper.insertDrink(drink2),
                            drinksRepoHelper.insertDrink(drink3),
                        Function3<Long,Long,Long,DefaultDrinksForBill>
            {t1, t2, t3 -> DefaultDrinksForBill(billId,t1,t2,t3) })

    }

    override fun createBill(bill: Bill): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}