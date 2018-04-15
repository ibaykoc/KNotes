package com.koc.knotes.repository

import android.arch.lifecycle.LiveData
import com.koc.knotes.KNotesApp
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.model.ui.NoteUiModel
import com.koc.knotes.util.toDataModel

class NoteRepo {

    companion object {
        private var mInstance: NoteRepo? = null
        val instance: NoteRepo
            get() {
                if(mInstance == null)
                    mInstance = NoteRepo()

                return mInstance!!
            }
    }

    fun getAllNote(): LiveData<List<NoteDataModel>> {
        return KNotesApp.database.KNotesDao().getAllNotes()
    }

    fun getSingleNote(noteId: Int): LiveData<NoteDataModel> {
        return KNotesApp.database.KNotesDao().getSingleNote(noteId)
    }

    fun insertNewNote(uiModel: NoteUiModel) {
        KNotesApp.database.KNotesDao().insertNewNote(uiModel.toDataModel)
    }

    fun updateNote(uiModel: NoteUiModel) {
        KNotesApp.database.KNotesDao().updateNote(uiModel.toDataModel)
    }

    fun deleteNote(uiModel: NoteUiModel) {
        KNotesApp.database.KNotesDao().deleteNote(uiModel.toDataModel)
    }
}