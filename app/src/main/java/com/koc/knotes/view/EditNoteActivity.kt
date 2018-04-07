package com.koc.knotes.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.koc.knotes.R
import com.koc.knotes.model.view.NoteUiModel
import com.koc.knotes.util.EDIT_NOT_EXTRA_ID
import com.koc.knotes.viewmodel.EditNoteViewModel
import com.koc.knotes.viewmodel.factory.EditNoteViewModelFactory
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {

    lateinit var viewModel: EditNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        viewModel = ViewModelProviders.of(this, EditNoteViewModelFactory(
                intent.getIntExtra(EDIT_NOT_EXTRA_ID, -1
                )))
                .get(EditNoteViewModel::class.java)
        viewModel.noteUiModel.observe(this, Observer {
            it?.let {
                updateUi(it)
            }
        })
        viewModel.noteUpdated.observe(this, Observer {
            it?.let{
                if(it)
                    finish()
            }
        })
    }

    private fun updateUi(data: NoteUiModel) {
        editText_title.setText(data.title)
        editText_note.setText(data.body)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.save -> {
                viewModel.updateDataModel(editText_title.text.toString(),
                        editText_note.text.toString())
                return true
            }
            R.id.delete -> {
                viewModel.deleteNote()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
