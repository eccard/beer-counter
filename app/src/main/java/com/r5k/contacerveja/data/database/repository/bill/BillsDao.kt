package com.r5k.contacerveja.data.database.repository.bill

import androidx.room.*

@Dao
interface BillsDao{

    // we can only have one bill opened

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bill: Bill) : Long

    @Query("SELECT * FROM bills ORDER BY date DESC")
    fun loadAllBills() : List<Bill>

    @Query("SELECT * FROM bills WHERE status=:state")
    fun loadBillWithState(state : Int) : List<Bill>

    @Update
    fun updateBill(bill : Bill) : Int

    @Query("UPDATE bills SET status=:state WHERE id=:billId")
    fun updateBillState(billId : Long,state : Int) : Int



}