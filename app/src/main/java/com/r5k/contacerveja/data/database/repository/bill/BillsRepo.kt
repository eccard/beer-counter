package com.r5k.contacerveja.data.database.repository.bill

import io.reactivex.Observable
import io.reactivex.Single

interface BillsRepo{

    fun isBillsReposEmpty() : Observable<Boolean>

    fun insertBiil(bill: Bill) : Single<Long>

    fun loadBiils() : Single<List<Bill>>

    fun loadOpenedBills() : Single<List<Bill>>

}