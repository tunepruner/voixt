package com.tunepruner.voixt.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tunepruner.voixt.R

val gillSansMedium = FontFamily(Font(R.font.gill_sans_medium, weight = FontWeight(300)))
val courier = FontFamily(Font(R.font.courier_prime_regular))
val juraBold = FontFamily(Font(R.font.jura_bold))

val defaults = Typography()

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = gillSansMedium,
        fontWeight = defaults.bodyLarge.fontWeight,
        fontSize = defaults.bodyLarge.fontSize,
        lineHeight = defaults.bodyLarge.lineHeight,
        letterSpacing = defaults.bodyLarge.letterSpacing,
    ),
    titleLarge = TextStyle(
        fontFamily = gillSansMedium,
        fontWeight = defaults.titleLarge.fontWeight,
        fontSize = defaults.titleLarge.fontSize,
        lineHeight = defaults.titleLarge.lineHeight,
        letterSpacing = defaults.titleLarge.letterSpacing,
    ),
    labelSmall = TextStyle(
        fontFamily = gillSansMedium,
        fontWeight = defaults.labelSmall.fontWeight,
        fontSize = defaults.labelSmall.fontSize,
        lineHeight = defaults.labelSmall.lineHeight,
        letterSpacing = defaults.labelSmall.letterSpacing,
    ),
    labelLarge = TextStyle(
        fontFamily = gillSansMedium,
        fontWeight = defaults.labelLarge.fontWeight,
        fontSize = defaults.labelLarge.fontSize,
        lineHeight = defaults.labelLarge.lineHeight,
        letterSpacing = defaults.labelLarge.letterSpacing,
    ),
    labelMedium = TextStyle(
        fontFamily = gillSansMedium,
        fontWeight = defaults.labelMedium.fontWeight,
        fontSize = defaults.labelMedium.fontSize,
        lineHeight = defaults.labelMedium.lineHeight,
        letterSpacing = defaults.labelMedium.letterSpacing,
    ),
    headlineLarge = TextStyle(
        fontFamily = juraBold,
        fontWeight = defaults.headlineLarge.fontWeight,
        fontSize = defaults.headlineLarge.fontSize,
        lineHeight = defaults.headlineLarge.lineHeight,
        letterSpacing = defaults.headlineLarge.letterSpacing,
    ),
)

val Typography.textBoat: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.courier_prime_regular)),
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }