package com.r5k.contacerveja.ui.historic.interactor

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.ui.base.MVPInteractor
import kotlinx.coroutines.Deferred

interface HistoricMVPInteractor : MVPInteractor {
    suspend fun loadBills() : Deferred<List<Bill>>
}