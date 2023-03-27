package com.juliangg.nails.database.turn

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TurnRepository @Inject constructor(private val turnDao: TurnDao){

    fun getAll() = turnDao.getAll()

    suspend fun getTurnsFromDate(date: String) : List<Turn> {
        Log.i("TAG", "getTurnsFromDate: $date")
        val output = turnDao.getTurnsFromDate(date)
        Log.i("TAG", "getTurnsFromDate: ${output.size}")
        return output
    }

    suspend fun update(turn: Turn) = turnDao.update(turn)

    suspend fun saveAll(turns: Turn) = turnDao.saveAll(turns)

    suspend fun delete(turn: Turn) = turnDao.delete(turn)
}