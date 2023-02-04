package com.juliangg.nails.database.turn

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TurnRepository @Inject constructor(private val turnDao: TurnDao){

    fun getAll() = turnDao.getAll()

    fun saveAll(turns: Turn) = turnDao.saveAll(turns)

    fun delete(turn: Turn) = turnDao.delete(turn)
}