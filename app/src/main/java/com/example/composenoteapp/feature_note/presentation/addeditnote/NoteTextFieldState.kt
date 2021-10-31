package com.example.composenoteapp.feature_note.presentation.addeditnote

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)
