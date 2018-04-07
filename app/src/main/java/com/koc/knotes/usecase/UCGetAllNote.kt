package com.koc.knotes.usecase

import android.arch.lifecycle.LiveData
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.repository.NoteRepo

class UCGetAllNote : UseCase {
    val noteRepo = NoteRepo.instance

    override fun execute(): LiveData<List<NoteDataModel>>{
        return noteRepo.getAllNote()
    }
}