package com.r5k.contacerveja.ui.historic.view

import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.ui.base.MVPView

interface HistoricMVPView :MVPView {

    fun showBills(bills : List<Bill>)
    fun openMainActivity()
}