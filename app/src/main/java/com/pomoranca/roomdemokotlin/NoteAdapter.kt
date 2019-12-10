package com.pomoranca.roomdemokotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var notes: List<Note> = arrayListOf()

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle = itemView.text_view_title!!
        val textViewPriority = itemView.text_view_priority!!
        val textViewDescription = itemView.text_view_description!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notes[position]
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.text = currentNote.priority.toString()

    }

}