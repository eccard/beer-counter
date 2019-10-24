package com.r5k.contacerveja.ui.historic

import com.r5k.contacerveja.ui.historic.interactor.HistoricInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HistoricActivityModule  {

    @Provides
    @Singleton
    internal fun providesInteractor(historicInteractor : HistoricInteractor) = historicInteractor

}