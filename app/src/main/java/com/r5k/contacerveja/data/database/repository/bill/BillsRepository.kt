package com.r5k.contacerveja.data.database.repository.bill

import javax.inject.Inject

class BillsRepository @Inject constructor(private val billsDao: BillsDao) : BillsRepo{

    override fun isBillsReposEmpty(): Boolean = billsDao.loadAllBills().isEmpty()

    override fun insertBill(bill: Bill): Long {
        return this.billsDao.insert(bill)
    }

    override fun loadBills(): List<Bill> =  billsDao.loadAllBills()

    override fun loadOpenedBills(): List<Bill>  = billsDao.loadBillWithState(BillState.OPEN.ordinal)

}
