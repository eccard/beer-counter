package com.r5k.contacerveja.data.database.repository.drink

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DrinksDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dring: Drink): Long

    @Query("SELECT * FROM drinks")
    fun loadAll(): List<Drink>
}