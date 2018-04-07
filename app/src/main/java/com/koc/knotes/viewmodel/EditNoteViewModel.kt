package com.koc.knotes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.koc.knotes.model.view.NoteUiModel
import com.koc.knotes.usecase.UCDeleteNote
import com.koc.knotes.usecase.UCGetSingleNote
import com.koc.knotes.usecase.UCUpdateNote
import com.koc.knotes.util.toUiModel
import io.reactivex.disposables.CompositeDisposable

class EditNoteViewModel(val noteId: Int) : ViewModel() {
    private val noteDataModel = UCGetSingleNote(noteId).execute()
    val noteUiModel = Transformations.map(noteDataModel, {
        it?.toUiModel
    })

    val loading = MutableLiveData<Boolean>()
    val noteUpdated = MutableLiveData<Boolean>()

    val compositeDisposable = CompositeDisposable()

    fun updateDataModel(title: String, body: String) {
        noteDataModel.value?.let {
            loading.value = true
            compositeDisposable.add(
                    UCUpdateNote(NoteUiModel(it.id, title, body)).execute()
                            .subscribe({
                                loading.value = false
                                noteUpdated.value = true
                            }, {

                            })
            )
        }
    }

    fun deleteNote() {
        noteUiModel.value?.let {
            compositeDisposable.add(
                    UCDeleteNote(it).execute()
                            .subscribe({
                                noteUpdated.value = true
                            },{

                            })
            )
        }
    }
}