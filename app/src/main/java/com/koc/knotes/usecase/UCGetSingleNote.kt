package com.koc.knotes.usecase

import android.arch.lifecycle.LiveData
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.repository.NoteRepo
import io.reactivex.Single

class UCGetSingleNote(val noteId:Int) : UseCase {
    val noteRepo = NoteRepo.instance
    override fun execute(): LiveData<NoteDataModel> {
        return noteRepo.getSingleNote(noteId)
    }
}