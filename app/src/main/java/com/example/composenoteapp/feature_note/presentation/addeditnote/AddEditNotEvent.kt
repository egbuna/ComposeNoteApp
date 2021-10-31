package com.example.composenoteapp.feature_note.presentation.addeditnote

import androidx.compose.ui.focus.FocusState

sealed class AddEditNotEvent {
    data class EnteredTitle(val value: String) : AddEditNotEvent()
    data class EnteredContent(val value: String): AddEditNotEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNotEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNotEvent()
    data class ChangeColor(val color: Int): AddEditNotEvent()
    object  SaveNote: AddEditNotEvent()

}
