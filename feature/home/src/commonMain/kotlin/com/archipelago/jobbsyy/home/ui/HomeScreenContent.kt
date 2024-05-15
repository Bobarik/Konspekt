package com.archipelago.jobbsyy.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun HomeScreenContent() = Column(
    modifier = Modifier.fillMaxSize()
        .windowInsetsPadding(WindowInsets.safeDrawing)
) {
    Text(text = "Home prikol")
}
