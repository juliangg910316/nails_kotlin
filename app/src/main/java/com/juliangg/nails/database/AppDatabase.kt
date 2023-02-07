package com.juliangg.nails.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juliangg.nails.database.turn.Turn
import com.juliangg.nails.database.turn.TurnDao

@Database(entities = [Turn::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun turnDao(): TurnDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "nails-db")
                    //.createFromAsset("database/nails.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}