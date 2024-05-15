package com.archipelago.jobbsyy.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.sp
import jobbsyy.core.design_system.generated.resources.Res
import jobbsyy.core.design_system.generated.resources.josefin_sans_400
import jobbsyy.core.design_system.generated.resources.josefin_sans_500
import jobbsyy.core.design_system.generated.resources.josefin_sans_600
import jobbsyy.core.design_system.generated.resources.josefin_sans_700
import org.jetbrains.compose.resources.Font

val JosefinSans
    @Composable
    get() = FontFamily(
        Font(Res.font.josefin_sans_400, W400),
        Font(Res.font.josefin_sans_500, W500),
        Font(Res.font.josefin_sans_600, W600),
        Font(Res.font.josefin_sans_700, W700),
    )

internal val AppTypography
    @Composable
    get() = Typography(
        headlineLarge = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Medium,
            fontSize = 36.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp
        ),
        titleLarge = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        ),
        titleMedium = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        ),
        titleSmall = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        ),
        bodySmall = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        labelLarge = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        ),
        labelMedium = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        labelSmall = TextStyle(
            fontFamily = JosefinSans,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        ),
    )
