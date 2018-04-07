package com.koc.knotes.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.koc.knotes.R
import com.koc.knotes.model.view.NoteUiModel
import com.koc.knotes.util.inflate
import com.koc.knotes.util.openCreateNote
import com.koc.knotes.util.openEditNote
import com.koc.knotes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_list_item.view.*

class MainActivity : AppCompatActivity() {

    val logTag = this::class.java.simpleName

    lateinit var viewModel: MainViewModel
    lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteListAdapter = NoteListAdapter()
        recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recView.adapter = noteListAdapter

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        fab.setOnClickListener {
            openCreateNote()
        }

        viewModel.viewNotes.observe(this, Observer {
            it?.let {
                noteListAdapter.changeData(it)
            }

        })

        viewModel.loading.observe(this, Observer {
            it?.let {
                Log.d(logTag,"Loading = $it")
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    inner class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.viewholder>() {

        private val logTag = this::class.java.simpleName

        private var notes = listOf<NoteUiModel>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
            return viewholder(parent.inflate(R.layout.note_list_item, false))
        }

        override fun getItemCount(): Int {
            return notes.size
        }

        override fun onBindViewHolder(holder: viewholder, position: Int) {
            with(holder.itemView) {
                title.text = notes[position].title
                body.text = notes[position].body
                more.setOnClickListener {
                    val popupMenu = PopupMenu(this@MainActivity,it)
                    val menu = popupMenu.menu

                    popupMenu.menuInflater.inflate(R.menu.note_item_more_menu, menu)
                    popupMenu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.edit -> {
                                this@MainActivity.openEditNote(notes[position].id)
                                true
                            }
                            R.id.delete -> {
                                this@MainActivity.viewModel.deleteNote(position)
                                true
                            }
                            else -> {
                                true
                            }
                        }
                    }

                    popupMenu.show()

                }
                setOnClickListener {
                    this@MainActivity.openEditNote(notes[position].id)
                }
            }
        }

        fun changeData(newData: List<NoteUiModel>) {
            notes = newData
            notifyDataSetChanged()
        }

        inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}
