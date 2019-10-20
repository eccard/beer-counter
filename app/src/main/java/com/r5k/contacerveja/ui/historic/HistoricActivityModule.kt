package com.r5k.contacerveja.ui.historic

import com.r5k.contacerveja.ui.historic.interactor.HistoricInteractor
import dagger.Module
import dagger.Provides

@Module
class HistoricActivityModule  {

    @Provides
    internal fun providesInteractor(historicInteractor : HistoricInteractor) = historicInteractor

}