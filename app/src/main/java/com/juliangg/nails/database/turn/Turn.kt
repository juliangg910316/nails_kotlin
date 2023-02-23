package com.juliangg.nails.database.turn

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single table in the database. Each row is a separate instance of the Turn class.
 * Each property corresponds to a column. Additionally, an ID is needed as a unique identifier for
 * each row in the database.
 */
@Entity(tableName = "turns")
data class Turn(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name_client") var nameClient: String = "",
    @ColumnInfo(name = "phone_client") var phoneClient: String = "",
    @ColumnInfo(name = "day") var day: String,
    @ColumnInfo(name = "hour") var hour: String = "0:00",
    @ColumnInfo(name = "am") var am: Boolean = true,
    @ColumnInfo(name = "pay_previous") var payPrevious: String = "$0.00",
    @ColumnInfo(name = "pay_total") var payTotal: String = "$0.00",
    @ColumnInfo(name = "image") var image: String = "",
    @ColumnInfo(name = "fails") var fails: Boolean = false
) {
    override fun toString(): String {
        return "Turn(id='$id', nameClient='$nameClient', phoneClient='$phoneClient', day='$day', " +
                "hour='$hour', am='$am', payPrevious='$payPrevious', payTotal='$payTotal', " +
                "image='$image', fails=$fails)"
    }
}
