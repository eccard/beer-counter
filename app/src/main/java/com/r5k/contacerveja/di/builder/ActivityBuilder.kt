package com.r5k.contacerveja.di.builder

import com.r5k.contacerveja.ui.main.view.MainActivity
import com.r5k.contacerveja.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity
}