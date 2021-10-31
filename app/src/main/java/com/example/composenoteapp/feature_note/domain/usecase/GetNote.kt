package com.example.composenoteapp.feature_note.domain.usecase

import com.example.composenoteapp.feature_note.domain.model.Note
import com.example.composenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNote @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(noteId: Int) =  repository.getNoteById(id = noteId)
}