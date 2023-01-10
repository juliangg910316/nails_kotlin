package com.juliangg.nails

import android.app.Application
import com.juliangg.nails.database.AppDatabase

class NailsApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}