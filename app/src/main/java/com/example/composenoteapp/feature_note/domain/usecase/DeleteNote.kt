package com.example.composenoteapp.feature_note.domain.usecase

import com.example.composenoteapp.feature_note.domain.model.Note
import com.example.composenoteapp.feature_note.domain.repository.NoteRepository

class DeleteNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note) = noteRepository.deleteNote(note)

}