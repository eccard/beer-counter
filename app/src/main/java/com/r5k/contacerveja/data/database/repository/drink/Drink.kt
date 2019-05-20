package com.r5k.contacerveja.data.database.repository.drink

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.r5k.contacerveja.data.database.repository.bill.Bill

@Entity(
    tableName = "drinks", foreignKeys = [(ForeignKey(
        entity = Bill::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("bill_id"),
        onDelete = ForeignKey.CASCADE
    ))], indices = arrayOf(Index(value = ["bill_id"]))
)

data class Drink(
    @Expose
    @PrimaryKey
    var id: Long?,

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @Expose
    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price: String?,

    @Expose
    @SerializedName("qnt")
    @ColumnInfo(name = "qnt")
    var qnt: Int,

    @Expose
    @SerializedName("bill_id")
    @ColumnInfo(name = "bill_id")
    var billId: Long

) : Parcelable {
    constructor(source: Parcel) : this(
        source.readValue(Long::class.java.classLoader) as Long?,
        source.readString(),
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int,
        source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(id)
        writeString(name)
        writeString(price)
        writeValue(qnt)
        writeLong(billId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Drink> = object : Parcelable.Creator<Drink> {
            override fun createFromParcel(source: Parcel): Drink = Drink(source)
            override fun newArray(size: Int): Array<Drink?> = arrayOfNulls(size)
        }
    }
}