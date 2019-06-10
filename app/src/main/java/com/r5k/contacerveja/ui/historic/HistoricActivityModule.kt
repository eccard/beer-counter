package com.r5k.contacerveja.ui.historic

import com.r5k.contacerveja.ui.historic.interactor.HistoricInteractor
import com.r5k.contacerveja.ui.historic.interactor.HistoricMVPInteractor
import com.r5k.contacerveja.ui.historic.presenter.HistoricMVPPresenter
import com.r5k.contacerveja.ui.historic.presenter.HistoricPresenter
import com.r5k.contacerveja.ui.historic.view.HistoricMVPView
import dagger.Module
import dagger.Provides

@Module
class HistoricActivityModule  {

    @Provides
    internal fun providesInteractor(historicInteractor : HistoricInteractor) : HistoricMVPInteractor = historicInteractor

    @Provides
    internal fun providesHistoricPresenter(historicPresenter : HistoricPresenter<HistoricMVPView,HistoricInteractor>)
    : HistoricMVPPresenter<HistoricMVPView,HistoricInteractor> = historicPresenter
}