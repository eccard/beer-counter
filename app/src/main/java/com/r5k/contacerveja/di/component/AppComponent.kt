package com.r5k.contacerveja.di.component

import android.app.Application
import com.r5k.contacerveja.CountBeerApp
import com.r5k.contacerveja.di.builder.ActivityBuilder
import com.r5k.contacerveja.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build() : AppComponent
    }

    fun inject(app: CountBeerApp)
}