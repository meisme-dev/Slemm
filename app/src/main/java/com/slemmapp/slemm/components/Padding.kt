package com.slemmapp.slemm.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Padding(padding: Dp) {
    Box(
        modifier = Modifier.padding(padding)
    )
}