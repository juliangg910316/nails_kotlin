package com.juliangg.nails.database.turn

import androidx.room.*
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
    suspend fun getTurnsFromDate(day: String): List<Turn>

    @Update
    suspend fun update(turn: Turn)

    @Insert
    suspend fun saveAll(vararg turns: Turn)

    @Delete
    suspend fun delete(turn: Turn)
}