package com.r5k.contacerveja.ui.main

import com.r5k.contacerveja.ui.main.interactor.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainActivityModule{

    @Provides
    @Singleton
    internal fun providesMainInteractor(mainInteractor: MainInteractor) = mainInteractor

}