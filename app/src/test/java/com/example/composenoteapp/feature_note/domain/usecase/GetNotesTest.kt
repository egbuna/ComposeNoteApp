package com.example.composenoteapp.feature_note.domain.usecase

import com.example.composenoteapp.feature_note.FakeNoteRepository
import com.example.composenoteapp.feature_note.domain.model.Note
import com.example.composenoteapp.feature_note.domain.util.NoteOrder
import com.example.composenoteapp.feature_note.domain.util.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetNotesTest {

    lateinit var getNotesUseCase: GetNotes
    lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNotesUseCase = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()

        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
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
    fun `Order notes by title, by Ascending`() = runBlocking {
        val notes = getNotesUseCase.invoke(NoteOrder.Title(orderType = OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by title, by Descending`() = runBlocking {
        val notes = getNotesUseCase.invoke(NoteOrder.Title(orderType = OrderType.Descending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by content, by Ascending`() = runBlocking {
        val notes = getNotesUseCase.invoke(NoteOrder.Color(orderType = OrderType.Ascending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].content).isLessThan(notes[i+1].content)
        }
    }

    @Test
    fun `Order notes by content, by Descending`() = runBlocking {
        val notes = getNotesUseCase.invoke(NoteOrder.Color(orderType = OrderType.Descending)).first()
        for (i in 0..notes.size - 2) {
            assertThat(notes[i].content).isGreaterThan(notes[i+1].content)
        }
    }
}