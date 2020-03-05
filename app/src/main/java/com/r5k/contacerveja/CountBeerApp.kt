package com.r5k.contacerveja

import android.app.Application
import com.r5k.contacerveja.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CountBeerApp : Application(), HasAndroidInjector{

    @Inject
    lateinit internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector() = activityDispatchingAndroidInjector


    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }
}