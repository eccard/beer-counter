package com.r5k.contacerveja.di.builder

import com.r5k.contacerveja.ui.drink.DrinkFragmentProvider
import com.r5k.contacerveja.ui.historic.HistoricActivityModule
import com.r5k.contacerveja.ui.historic.view.HistoricActivity
import com.r5k.contacerveja.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector(modules = [DrinkFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(HistoricActivityModule::class)])
    abstract fun bindHistoricActivity(): HistoricActivity
}