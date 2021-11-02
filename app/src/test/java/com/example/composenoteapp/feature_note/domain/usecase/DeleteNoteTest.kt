package com.example.composenoteapp.feature_note.domain.usecase

import com.example.composenoteapp.feature_note.FakeNoteRepository
import com.example.composenoteapp.feature_note.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class DeleteNoteTest {

    lateinit var deleteNoteUseCase: DeleteNote
    lateinit var getNotesUseCase: GetNotes
    lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {

        fakeNoteRepository = FakeNoteRepository()
        deleteNoteUseCase = DeleteNote(fakeNoteRepository)
        getNotesUseCase = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()

        ('a'..'c').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index,
                    id = index+1
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach {
                fakeNoteRepository.insertNote(
                    it
                )
            }
        }
    }

    @Test
    fun `Delete note`() = runBlocking {
        val notes = getNotesUseCase.invoke().first()
        assertThat(notes).hasSize(3)
        deleteNoteUseCase(notes[0])
        val deletedNotes = getNotesUseCase.invoke().first()
        assertThat(deletedNotes).hasSize(2)
    }
}