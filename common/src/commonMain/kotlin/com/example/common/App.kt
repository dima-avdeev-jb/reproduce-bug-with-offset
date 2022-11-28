package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

val WIDTH = 100.dp
@Composable
fun ReproduceOffsetBug() {
    Column {
        CheckVisibility("Visible", -WIDTH)
        CheckVisibility("Not visible on Desktop", -WIDTH - 1.dp) // todo +1 changes visibility on Desktop
    }
}
@Composable
fun CheckVisibility(label: String, offsetX: Dp) {
    val offsetPx = with(LocalDensity.current) {
        IntOffset(offsetX.toPx().toInt(), 0)
    }
    Box(
        Modifier.size(WIDTH)
            .offset { offsetPx } // todo offset with lambda work's BAD
//            .offset(offsetX) // offset with arguments works GOOD
    ) {
        Text(label, Modifier.offset(WIDTH).background(Color.LightGray))
    }
}
