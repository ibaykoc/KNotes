package com.koc.knotes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.koc.knotes.model.ui.NoteUiModel
import com.koc.knotes.usecase.UCInsertNewNote

class CreateNoteViewModel : ViewModel() {

    val note = MutableLiveData<NoteUiModel>()
    val loading = MutableLiveData<Boolean>()
    val saved = MutableLiveData<Boolean>()

    init {
        note.value = NoteUiModel(0, "", "")
    }

    fun save() {
        note.value?.let {
            loading.value = true
            UCInsertNewNote(it).execute()
                    .subscribe({
                        loading.value = false
                        saved.value = true
                    },{

                    })
        }
    }
}