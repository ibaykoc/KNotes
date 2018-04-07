package com.koc.knotes.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.koc.knotes.viewmodel.EditNoteViewModel

class EditNoteViewModelFactory(val noteId:Int):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditNoteViewModel(noteId) as T
    }
}