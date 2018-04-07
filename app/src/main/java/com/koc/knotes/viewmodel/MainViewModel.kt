package com.koc.knotes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.koc.knotes.usecase.UCGetAllNote
import com.koc.knotes.util.toUiModel

class MainViewModel : ViewModel() {
    private val notes = UCGetAllNote().execute()
    val loading = MutableLiveData<Boolean>()

    val viewNotes = Transformations.map(notes, {
        it.toUiModel
    })
}