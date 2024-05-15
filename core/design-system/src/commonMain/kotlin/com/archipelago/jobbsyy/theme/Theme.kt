package com.archipelago.jobbsyy.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimaryText,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryText,
    secondary = Secondary,
    onSecondary = OnSecondaryText,
    tertiary = Tertiary,
    onTertiary = OnTertiaryText,
    background = Background,
    onBackground = PrimaryText,
    surface = Surface,
    onSurface = OnSurface,
    outline = Outline,
    surfaceTint = SurfaceTint,
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(2.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) = MaterialTheme(
    colorScheme = LightColorScheme,
    typography = AppTypography,
    shapes = AppShapes,
    content = content
)
