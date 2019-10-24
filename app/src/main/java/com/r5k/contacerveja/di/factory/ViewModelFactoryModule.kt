package com.r5k.contacerveja.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r5k.contacerveja.ui.drink.view.DrinkViewModel
import com.r5k.contacerveja.ui.historic.view.HistoricViewModel
import com.r5k.contacerveja.ui.main.view.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(DrinkViewModel::class)
    abstract fun bindDrinkViewModel(drinkViewModel: DrinkViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoricViewModel::class)
    abstract fun bindHistoricViewModel(historicViewModel: HistoricViewModel) : ViewModel

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}