package com.koc.knotes.usecase

import com.koc.knotes.model.view.NoteUiModel
import com.koc.knotes.repository.NoteRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCSaveNote(val noteUiModel: NoteUiModel) : UseCase {
    val noteRepo = NoteRepo.instance


    override fun execute(): Single<Unit> {
        return Single.fromCallable {
            noteRepo.saveNote(noteUiModel)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}