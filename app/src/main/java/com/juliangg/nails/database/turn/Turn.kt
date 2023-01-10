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
@Entity
data class Turn(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "name_client") val nameClient: String,
    @NonNull @ColumnInfo(name = "phone_client") val phoneClient: String,
    @NonNull @ColumnInfo(name = "date") val date: String,
    @NonNull @ColumnInfo(name = "pay_previous") val payPrevious: String,
    @NonNull @ColumnInfo(name = "pay_total") val payTotal: String,
    @NonNull @ColumnInfo(name = "image") val image: String,
    @NonNull @ColumnInfo(name = "fails") val fails: Boolean
)
