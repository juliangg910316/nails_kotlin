package com.juliangg.nails.database.turn

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TurnRepository @Inject constructor(private val turnDao: TurnDao){

    fun getAll() = turnDao.getAll()

    fun getTurnsFromDate(date: String) : Flow<List<Turn>> {
        Log.i("TAG", "getTurnsFromDate: $date")
        return turnDao.getTurnsFromDate(date)
    }

    suspend fun saveAll(turns: Turn) = turnDao.saveAll(turns)

    suspend fun delete(turn: Turn) = turnDao.delete(turn)
}