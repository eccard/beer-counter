package com.r5k.contacerveja.ui.historic.presenter

import com.r5k.contacerveja.ui.base.MVPPresenter
import com.r5k.contacerveja.ui.historic.interactor.HistoricMVPInteractor
import com.r5k.contacerveja.ui.historic.view.HistoricMVPView

interface HistoricMVPPresenter<V : HistoricMVPView, I : HistoricMVPInteractor> : MVPPresenter<V,I> {

    fun loadBiils()
}