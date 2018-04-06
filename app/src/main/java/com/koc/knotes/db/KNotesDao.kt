package com.koc.knotes.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.koc.knotes.model.data.NoteDataModel
import io.reactivex.Single

@Dao
interface KNotesDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<NoteDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteDataModel: NoteDataModel)
}