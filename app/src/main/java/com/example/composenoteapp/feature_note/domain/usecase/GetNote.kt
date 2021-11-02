package com.example.composenoteapp.feature_note.domain.usecase

import com.example.composenoteapp.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class GetNote @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(noteId: Int) =  repository.getNoteById(id = noteId)
}