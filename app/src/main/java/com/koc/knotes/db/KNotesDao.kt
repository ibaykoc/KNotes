package com.koc.knotes.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.koc.knotes.model.data.NoteDataModel
import io.reactivex.Single

@Dao
interface KNotesDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<NoteDataModel>>

    @Query("SELECT * FROM notes WHERE noteId = :noteId")
    fun getSingleNote(noteId:Int): LiveData<NoteDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewNote(noteDataModel: NoteDataModel)

    @Update
    fun updateNote(noteDataModel: NoteDataModel)

    @Delete
    fun deleteNote(noteDataModel: NoteDataModel)
}