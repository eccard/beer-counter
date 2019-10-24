package com.r5k.contacerveja.ui.drink

import com.r5k.contacerveja.ui.drink.interactor.DrinkInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DrinkFragmentModule {

    @Provides
    @Singleton
    internal fun providesDrinkIterator(drinkIterator : DrinkInteractor)  = drinkIterator
}