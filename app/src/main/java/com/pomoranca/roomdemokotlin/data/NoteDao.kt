package com.pomoranca.roomdemokotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pomoranca.roomdemokotlin.data.Note


@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>



}