package com.koc.knotes.model.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDataModel (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "noteId")
        val id:Int,

        @ColumnInfo(name = "note_title")
        val title:String,

        @ColumnInfo(name = "note_body")
        val body:String
)