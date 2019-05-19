package com.r5k.contacerveja.data.database.repository.drink

import androidx.room.*

@Dao
interface DrinksDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dring: Drink): Long

    @Query("SELECT * FROM drinks")
    fun loadAll(): List<Drink>

    @Query("SELECT * FROM drinks WHERE bill_id=:billId")
    fun loadDrinksFromBillId(billId : Long): List<Drink>

    @Update
    fun updateDrink(drink : Drink) : Int
}