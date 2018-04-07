package com.koc.knotes.util

import android.app.Activity
import android.content.Intent
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koc.knotes.model.data.NoteDataModel
import com.koc.knotes.model.view.NoteUiModel
import com.koc.knotes.view.CreateNoteActivity
import com.koc.knotes.view.EditNoteActivity

val NoteUiModel.toDataModel: NoteDataModel
    get() {
        return NoteDataModel(this.id, this.title, this.body)
    }

val NoteDataModel.toUiModel: NoteUiModel
    get() {
        return NoteUiModel(id, title, body)
    }

val List<NoteDataModel>.toUiModel: List<NoteUiModel>
    get() {
        val result = mutableListOf<NoteUiModel>()
        for (d in this) {
            result.add(d.toUiModel)
        }
        return result
    }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Activity.openCreateNote(){
    startActivity(Intent(this, CreateNoteActivity::class.java))
}

fun Activity.openEditNote(noteId:Int){
    val i = Intent(this, EditNoteActivity::class.java)
    i.putExtra(EDIT_NOT_EXTRA_ID, noteId)
    startActivity(i)
}