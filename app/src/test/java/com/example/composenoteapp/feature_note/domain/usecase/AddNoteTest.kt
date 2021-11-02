package com.example.composenoteapp.feature_note.domain.usecase

import com.example.composenoteapp.feature_note.FakeNoteRepository
import com.example.composenoteapp.feature_note.domain.model.InvalidNoteException
import com.example.composenoteapp.feature_note.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class AddNoteTest {

    lateinit var addNoteUseCase: AddNote
    lateinit var getNotesUseCase: GetNotes
    lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {

        fakeNoteRepository = FakeNoteRepository()
        addNoteUseCase = AddNote(fakeNoteRepository)
        getNotesUseCase = GetNotes(fakeNoteRepository)
    }

    @Test
    fun `throw error if title is blank`() = runBlocking {
        try {
            addNoteUseCase.invoke(
                Note(
                    id = 1,
                    title = "",
                    content = "lkjenrjkner",
                    timestamp = 1.toLong(),
                    color = 1
                )
            )
        } catch (e : InvalidNoteException) {
            assertThat(e.message).isEqualTo("The title of the note can't be empty.")
        }
    }

    @Test
    fun `throw error if content is blank`() = runBlocking {
        try {
            addNoteUseCase.invoke(
                Note(
                    id = 1,
                    title = "wefjbejrke",
                    content = "",
                    timestamp = 1.toLong(),
                    color = 1
                )
            )
        } catch (e : InvalidNoteException) {
            assertThat(e.message).isEqualTo("The content of the note can't be empty.")
        }
    }

    @Test
    fun `Add a note`() = runBlocking {
        addNoteUseCase.invoke(
            Note(
                id = 1,
                title = "Title",
                content = "Content",
                timestamp = 1.toLong(),
                color = 1
            )
        )
        val notes = getNotesUseCase.invoke().first()
        assertThat(notes).hasSize(1)
    }
}