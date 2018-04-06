package com.koc.knotes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.koc.knotes.model.view.NoteViewModel
import com.koc.knotes.usecase.SaveNote

class CreateNoteViewModel : ViewModel() {

    val note = MutableLiveData<NoteViewModel>()
    val loading = MutableLiveData<Boolean>()
    val saved = MutableLiveData<Boolean>()

    init {
        note.value = NoteViewModel("", "")
    }

    fun save() {
        note.value?.let {
            loading.value = true
            SaveNote(it).execute()
                    .subscribe({
                        loading.value = false
                        saved.value = true
                    },{

                    })
        }
    }
}