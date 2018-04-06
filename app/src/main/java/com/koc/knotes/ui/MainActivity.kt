package com.koc.knotes.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.koc.knotes.R
import com.koc.knotes.model.view.NoteViewModel
import com.koc.knotes.util.inflate
import com.koc.knotes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note_list_item.view.*

class MainActivity : AppCompatActivity() {

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
            startActivity(Intent(this, CreateNoteActivity::class.java))
        }

        viewModel.viewNotes.observe(this, object : Observer<List<NoteViewModel>> {
            override fun onChanged(t: List<NoteViewModel>?) {
                t?.let {
                    noteListAdapter.changeData(it)
                }
            }
        })

        viewModel.loading.observe(this, Observer {
            it?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.viewholder>() {

        private var notes = listOf<NoteViewModel>()

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
            }
        }

        fun changeData(newData: List<NoteViewModel>) {
            notes = newData
            notifyDataSetChanged()
        }

        class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}
