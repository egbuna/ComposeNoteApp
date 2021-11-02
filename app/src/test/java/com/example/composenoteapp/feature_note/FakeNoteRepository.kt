package com.example.composenoteapp.feature_note

import com.example.composenoteapp.feature_note.domain.model.Note
import com.example.composenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository : NoteRepository {

    private val fakeNotes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow {
            emit(fakeNotes)
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return fakeNotes.find { it.id == id }
    }

    override suspend fun deleteNote(note: Note) {
        fakeNotes.remove(note)
    }

    override suspend fun insertNote(note: Note) {
        fakeNotes.add(note)
    }

}