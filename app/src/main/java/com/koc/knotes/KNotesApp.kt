package com.koc.knotes

import android.app.Application
import android.arch.persistence.room.Room
import com.koc.knotes.db.KNotesDatabase

class KNotesApp:Application() {
    companion object {
        lateinit var database: KNotesDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, KNotesDatabase::class.java,"KNotesDB").build()
    }
}