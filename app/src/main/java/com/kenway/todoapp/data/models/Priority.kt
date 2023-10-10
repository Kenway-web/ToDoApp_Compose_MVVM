package com.kenway.todoapp.data.models

import androidx.compose.ui.graphics.Color
import com.kenway.todoapp.ui.theme.HighPriorityColor
import com.kenway.todoapp.ui.theme.LowPriorityColor
import com.kenway.todoapp.ui.theme.MediumPriorityColor
import com.kenway.todoapp.ui.theme.NonePriorityColor


enum class Priority(val color: Color) {

    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}