package com.koc.knotes.repository

import android.arch.lifecycle.LiveData
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.model.view.NoteViewModel
import io.reactivex.Single

interface INoteRepo {
    fun getAllNote(): LiveData<List<NoteDataModel>>

    fun saveNote(viewModel:NoteViewModel)
}