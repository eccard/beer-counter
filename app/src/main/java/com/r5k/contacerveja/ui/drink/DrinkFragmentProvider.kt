package com.r5k.contacerveja.ui.drink

import com.r5k.contacerveja.ui.drink.view.DrinkFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class DrinkFragmentProvider{

    @ContributesAndroidInjector(modules = [(DrinkFragmentModule::class)])
    internal abstract fun provideDrinkFragmentFactory(): DrinkFragment
}