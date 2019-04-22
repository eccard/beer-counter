package com.r5k.contacerveja.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillsDao
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksDao


@Database(entities = [(Bill::class), (Drink::class)], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun drinksDao(): DrinksDao

    abstract fun billsDao(): BillsDao
}