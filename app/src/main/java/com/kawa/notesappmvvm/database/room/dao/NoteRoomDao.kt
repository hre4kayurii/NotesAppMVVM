package com.kawa.notesappmvvm.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kawa.notesappmvvm.model.Note
import com.kawa.notesappmvvm.utils.Constants

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM ${Constants.Keys.NOTES_TABLE}")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}