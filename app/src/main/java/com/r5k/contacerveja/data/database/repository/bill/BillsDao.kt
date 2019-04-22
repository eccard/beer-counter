package com.r5k.contacerveja.data.database.repository.bill

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BillsDao{

    // we can only have one bill opened

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bill: Bill) : Long

    @Query("SELECT * FROM bills")
    fun loadAllBills() : List<Bill>

    @Query("SELECT * FROM bills WHERE status=1")
    fun loadOpenedBill() : List<Bill>


}