package com.koc.knotes.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.model.view.NoteViewModel
import com.koc.knotes.repository.NoteRepo
import com.koc.knotes.usecase.GetAllNote
import com.koc.knotes.util.EmptyMutableListLiveData
import com.koc.knotes.util.toViewModel

class MainViewModel : ViewModel() {
    val noteRepo = NoteRepo.instance

    private val notes = GetAllNote().execute()
    val loading = MutableLiveData<Boolean>()

    val viewNotes = Transformations.map(notes, {
        it.toViewModel
    })
}