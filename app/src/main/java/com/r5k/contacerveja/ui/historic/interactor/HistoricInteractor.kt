package com.r5k.contacerveja.ui.historic.interactor

import com.r5k.contacerveja.data.database.repository.bill.BillsRepository
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.base.BaseInteractor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class HistoricInteractor @Inject internal constructor(private val drinksRepoHelper: DrinksRepository,
                                                      private val billsRepository: BillsRepository): BaseInteractor(), HistoricMVPInteractor {

    override suspend fun loadBills() = GlobalScope.async {
        billsRepository.loadBills()
    }
}