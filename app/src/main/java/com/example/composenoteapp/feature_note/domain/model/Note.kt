package com.example.composenoteapp.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composenoteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int?
) {

    companion object {
        val colors = listOf(BabyBlue, RedOrange, Violet, RedPink, LightGreen)
    }
}

class InvalidNoteException(errorMessage: String): Exception(errorMessage)