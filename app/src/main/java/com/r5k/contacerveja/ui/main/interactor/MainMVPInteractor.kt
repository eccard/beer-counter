package com.r5k.contacerveja.ui.main.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillsRepository
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.base.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class MainMVPInteractor @Inject internal constructor(private val drinksRepoHelper: DrinksRepository, private val billsRepository: BillsRepository ): BaseInteractor(),MainVMPInteractor{

    override fun getBillData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //appDatabase.

//        billsRepository.loadBiils()
    }

    override fun getOpenedBill(): Single<List<Bill>> = billsRepository.loadOpenedBills()

    override fun createBill(bill: Bill): Single<Boolean> = billsRepository.insertBiil(bill)

    override fun loadDrinksFromBillId(billId: Long): Single<List<Drink>>
            = drinksRepoHelper.loadDrinksFromBillId(billId)
}