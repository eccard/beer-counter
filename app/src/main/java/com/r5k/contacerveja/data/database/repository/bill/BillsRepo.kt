package com.r5k.contacerveja.data.database.repository.bill


interface BillsRepo{

    fun isBillsReposEmpty() : Boolean

    fun insertBill(bill: Bill) : Long

    fun loadBills() : List<Bill>

    fun loadOpenedBills() : List<Bill>

    fun closeBil(billId: Long) : Int

}