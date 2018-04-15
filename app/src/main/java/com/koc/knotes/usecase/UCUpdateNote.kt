package com.koc.knotes.usecase

import com.koc.knotes.model.ui.NoteUiModel
import com.koc.knotes.repository.NoteRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UCUpdateNote(private val noteUiModel: NoteUiModel) : UseCase {
    private val noteRepo = NoteRepo.instance

    override fun execute(): Single<Unit> {
        return Single.fromCallable{
            noteRepo.updateNote(noteUiModel)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}