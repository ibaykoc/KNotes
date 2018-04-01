package com.koc.knotes.repository

import android.arch.lifecycle.LiveData
import com.koc.knotes.KNotesApp
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.model.view.NoteViewModel
import com.koc.knotes.util.toDataModel
import io.reactivex.Single

class NoteRepo : INoteRepo {

    companion object {
        private var mInstance: NoteRepo? = null
        val instance: NoteRepo
            get() {
                if(mInstance == null)
                    mInstance = NoteRepo()

                return mInstance!!
            }
    }

    override fun getAllNote(): LiveData<List<NoteDataModel>> {
        return KNotesApp.database.KNotesDao().getAllNotes()
    }

    override fun saveNote(viewModel: NoteViewModel) {
        KNotesApp.database.KNotesDao().insertNote(viewModel.toDataModel)
    }
}