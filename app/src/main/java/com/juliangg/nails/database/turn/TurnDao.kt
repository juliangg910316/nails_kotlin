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

    @Query("SELECT * FROM turns ORDER BY day ASC")
    fun getAll(): Flow<List<Turn>>

    @Query("SELECT * FROM turns where day = :day")
    fun getTurnsFromDate(day: String): Flow<List<Turn>>

    @Insert
    suspend fun saveAll(vararg turns: Turn)

    @Delete
    suspend fun delete(turn: Turn)
}