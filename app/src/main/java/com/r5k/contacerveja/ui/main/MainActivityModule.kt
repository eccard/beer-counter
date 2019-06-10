package com.r5k.contacerveja.ui.main

import com.r5k.contacerveja.ui.main.interactor.MainInteractor
import com.r5k.contacerveja.ui.main.interactor.MainMVPInteractor
import com.r5k.contacerveja.ui.main.presenter.MainMVPPresenter
import com.r5k.contacerveja.ui.main.presenter.MainPresenter
import com.r5k.contacerveja.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule{

    @Provides
    internal fun providesMainInteractor(mainMVPInteractor: MainInteractor): MainMVPInteractor = mainMVPInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView,MainInteractor>)
        : MainMVPPresenter<MainMVPView,MainInteractor> = mainPresenter
}