package com.example.composenoteapp.feature_note.domain.usecase

import com.example.composenoteapp.feature_note.FakeNoteRepository
import com.example.composenoteapp.feature_note.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetNoteTest {

    lateinit var getNoteUseCase: GetNote
    lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {

        fakeNoteRepository = FakeNoteRepository()
        getNoteUseCase = GetNote(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()

        ('a'..'z').forEachIndexed { index, c ->
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
    fun `Get single note by id`() = runBlocking {
        val note = getNoteUseCase(1)
        assertThat(note).isNotNull()
        assertThat(note?.id).isEqualTo(1)
    }
}