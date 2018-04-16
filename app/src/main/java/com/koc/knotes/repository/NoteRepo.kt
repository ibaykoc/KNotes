package com.koc.knotes.repository

import android.arch.lifecycle.LiveData
import com.koc.knotes.KNotesApp
import com.koc.knotes.db.KNotesDatabase
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.model.ui.NoteUiModel
import com.koc.knotes.util.toDataModel

class NoteRepo {


    companion object {
        lateinit var database: KNotesDatabase
        private var mInstance: NoteRepo? = null
        val instance: NoteRepo
            get() {
                if (mInstance == null)
                    mInstance = NoteRepo()

                return mInstance!!
            }

        fun init(database: KNotesDatabase) {
            this.database = database
        }
    }

    fun getAllNote(): LiveData<List<NoteDataModel>> {
        return database.KNotesDao().getAllNotes()
    }

    fun getSingleNote(noteId: Int): LiveData<NoteDataModel> {
        return database.KNotesDao().getSingleNote(noteId)
    }

    fun insertNewNote(uiModel: NoteUiModel) {
        database.KNotesDao().insertNewNote(uiModel.toDataModel)
    }

    fun updateNote(uiModel: NoteUiModel) {
        database.KNotesDao().updateNote(uiModel.toDataModel)
    }

    fun deleteNote(uiModel: NoteUiModel) {
        database.KNotesDao().deleteNote(uiModel.toDataModel)
    }
}