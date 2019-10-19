package com.r5k.contacerveja.ui.historic.presenter

import com.r5k.contacerveja.ui.base.BasePresenter
import com.r5k.contacerveja.ui.historic.interactor.HistoricMVPInteractor
import com.r5k.contacerveja.ui.historic.view.HistoricMVPView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistoricPresenter<V : HistoricMVPView, I :HistoricMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V,I>(interactor = interactor),HistoricMVPPresenter<V,I>{


    override fun onAttach(view: V?) {
        super.onAttach(view)

        loadBills()
    }

    override fun loadBills() {

        GlobalScope.launch(context = Dispatchers.Main) {

            val billList = withContext(context = Dispatchers.IO) {
                interactor!!.loadBills().await()
            }

            getView()?.showBills(billList)

        }
    }

}