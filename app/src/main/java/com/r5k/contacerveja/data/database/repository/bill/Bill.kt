package com.r5k.contacerveja.data.database.repository.bill

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "bills")
data class Bill(
    @Expose
    @PrimaryKey
    var id: Long? = null,

    @Expose
    @ColumnInfo(name = "date")
    val date:Long? = null,

    @Expose
    @ColumnInfo(name = "status")
    val status: Int = 1,


    @Expose
    @ColumnInfo(name = "amount")
    val amount: Float = 0F

)

