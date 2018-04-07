package com.koc.knotes.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.koc.knotes.usecase.UCDeleteNote
import com.koc.knotes.usecase.UCGetAllNote
import com.koc.knotes.util.toUiModel
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {
    private val notes = UCGetAllNote().execute()
    val loading = MutableLiveData<Boolean>()

    val compositeDisposable = CompositeDisposable()

    val viewNotes = Transformations.map(notes, {
        it.toUiModel
    })


    fun deleteNote(index:Int) {
        viewNotes.value?.let {
            compositeDisposable.add(
                    UCDeleteNote(it[index]).execute()
                            .subscribe({
                            },{

                            })
            )
        }
    }
}