package com.r5k.contacerveja.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.r5k.contacerveja.data.database.AppDatabase
import com.r5k.contacerveja.data.database.repository.bill.BillsRepo
import com.r5k.contacerveja.data.database.repository.bill.BillsRepository
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepo
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.util.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.APP_DB_NAME).build()

    @Provides
    @Singleton
    internal fun provideDrinksDao(appDatabase: AppDatabase)= appDatabase.drinksDao()

    @Provides
    @Singleton
    internal fun provideBillsDao(appDatabase: AppDatabase)= appDatabase.billsDao()


    @Provides
    @Singleton
    internal fun provideDrinksRepoHelper(appDatabase: AppDatabase): DrinksRepo = DrinksRepository(appDatabase.drinksDao())

    @Provides
    @Singleton
    internal fun provideBillsRepoHelper(appDatabase: AppDatabase): BillsRepo = BillsRepository(appDatabase.billsDao())


}