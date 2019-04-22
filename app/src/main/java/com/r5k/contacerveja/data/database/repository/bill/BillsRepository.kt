package com.r5k.contacerveja.data.database.repository.bill

import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class BillsRepository @Inject constructor(private val billsDao: BillsDao) : BillsRepo{

    override fun isBillsReposEmpty(): Observable<Boolean> = Observable.just(billsDao.loadAllBills().isEmpty())

    override fun insertBiil(bill: Bill): Single<Boolean> {
        return Single.just(this.billsDao.insert(bill) != -1L)
    }

    override fun loadBiils(): Single<List<Bill>>
     = Single.fromCallable({ billsDao.loadAllBills() })

    override fun loadOpenedBills(): Single<List<Bill>>  = Single.fromCallable({billsDao.loadOpenedBill()})
}
