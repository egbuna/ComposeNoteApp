package com.example.composenoteapp.feature_note.domain.util

import com.example.composenoteapp.feature_note.domain.model.Note

data class NoteState(
    var notes: List<Note> = emptyList(),
    var filterSectionVisibility: Boolean = false,
    var noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
)