package com.r5k.contacerveja.ui.drink

import com.r5k.contacerveja.ui.drink.view.DrinkFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DrinkFragmentProvider {

    @ContributesAndroidInjector(modules = [DrinkFragmentModule::class])
    abstract fun provideDrinkFragment(): DrinkFragment
}