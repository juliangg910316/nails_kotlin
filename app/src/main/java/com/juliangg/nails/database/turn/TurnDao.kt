package com.juliangg.nails.database.turn

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to read/write operations on the turn table.
 * Used by the view models to format the query results for use in the UI.
 */
@Dao
interface TurnDao {

    @Query("SELECT * FROM turn ORDER BY date ASC")
    fun getAll(): Flow<List<Turn>>

    @Insert
    fun saveAll(vararg turns: Turn)

    @Delete
    fun delete(turn: Turn)
}