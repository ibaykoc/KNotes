package com.koc.knotes.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.koc.knotes.R
import com.koc.knotes.viewmodel.CreateNoteViewModel
import kotlinx.android.synthetic.main.activity_create_note.*

class CreateNoteActivity : AppCompatActivity() {

    lateinit var viewModel: CreateNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        viewModel = ViewModelProviders.of(this).get(CreateNoteViewModel::class.java)
        editText_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    viewModel.note.value?.title = it.toString()
                }
            }
        })
        editText_note.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    viewModel.note.value?.body = it.toString()
                }
            }
        })
        viewModel.loading.observe(this, Observer {
            it?.let{
                if(it)
                    println("Loading")
                else
                    println("Loaded")
            }
        })

        viewModel.saved.observe(this, Observer {
            it?.let{
                if(it)
                    println("Saved")
                finish()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        editText_title.setText(viewModel.note.value?.title, TextView.BufferType.EDITABLE)
        editText_note.setText(viewModel.note.value?.body, TextView.BufferType.EDITABLE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.save -> {
                viewModel.save()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
