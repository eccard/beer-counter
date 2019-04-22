package com.r5k.contacerveja.data.database.repository.drink

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.r5k.contacerveja.data.database.repository.bill.Bill

@Entity(tableName = "drinks", foreignKeys = [(ForeignKey(entity = Bill::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("bill_id"),
    onDelete = ForeignKey.CASCADE))],indices = arrayOf(Index(value = ["bill_id"])))

data class Drink(
    @Expose
    @PrimaryKey
    var id: Long,

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String,

    @Expose
    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: String,

    @Expose
    @SerializedName("qnt")
    @ColumnInfo(name = "qnt")
    val qnt:Int,

    @Expose
    @SerializedName("bill_id")
    @ColumnInfo(name = "bill_id")
    var billId: Long

)