package com.koc.knotes.util

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.model.view.NoteViewModel

val NoteViewModel.toDataModel: NoteDataModel
    get() {
        return NoteDataModel(0, this.title, this.body)
    }

val NoteDataModel.toViewModel: NoteViewModel
    get() {
        return NoteViewModel(this.title, this.body)
    }

val List<NoteDataModel>.toViewModel: List<NoteViewModel>
    get() {
        val result = mutableListOf<NoteViewModel>()
        for (d in this) {
            result.add(d.toViewModel)
        }
        return result
    }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}