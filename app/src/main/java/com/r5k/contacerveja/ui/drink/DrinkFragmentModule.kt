package com.r5k.contacerveja.ui.drink

import com.r5k.contacerveja.ui.drink.interactor.DrinkInteractor
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.presenter.DrinkMVPPresenter
import com.r5k.contacerveja.ui.drink.presenter.DrinkPresenter
import com.r5k.contacerveja.ui.drink.view.DrinkMVPView
import dagger.Module
import dagger.Provides

@Module
class DrinkFragmentModule{

    @Provides
    internal fun providesDrinkIterator(iterator : DrinkInteractor) : DrinkMVPInteractor = iterator

    @Provides
    internal fun provideDrinkPresenter(presenter : DrinkPresenter<DrinkMVPView,DrinkMVPInteractor>)
    : DrinkMVPPresenter<DrinkMVPView,DrinkMVPInteractor> = presenter


}