package com.koc.knotes.db

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.koc.knotes.KNotesApp
import com.koc.knotes.model.data.NoteDataModel

@Database(entities = [(NoteDataModel::class)], version = 1)
abstract class KNotesDatabase:RoomDatabase(){
    abstract fun KNotesDao(): KNotesDao


}