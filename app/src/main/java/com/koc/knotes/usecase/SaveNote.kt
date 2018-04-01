package com.koc.knotes.usecase

import com.koc.knotes.model.view.NoteViewModel
import com.koc.knotes.repository.NoteRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaveNote(val noteViewModel: NoteViewModel) : UseCase {
    val noteRepo = NoteRepo.instance


    override fun execute(): Single<Unit> {
        return Single.fromCallable {
            noteRepo.saveNote(noteViewModel)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}