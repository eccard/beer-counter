package com.r5k.contacerveja.data.database.repository.bill


interface BillsRepo{

    fun isBillsReposEmpty() : Boolean

    fun insertBiil(bill: Bill) : Long

    fun loadBiils() : List<Bill>

    fun loadOpenedBills() : List<Bill>

}