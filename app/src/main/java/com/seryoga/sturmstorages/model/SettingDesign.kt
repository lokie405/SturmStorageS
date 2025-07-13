package com.seryoga.sturmstorages.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import com.seryoga.sturmstorages.ui.theme.Font

/**
 * For set parameter inside Row Items represent
 * */
data class SettingDesign(
    val name: String = "",
    val color: Int = Color.Yellow.toArgb(),
    val size: Int = 0,
    val font: FontFamily? = Font.mapFontsFamily[Font.JET_BRAIN],
    val backgroundColor: Int = 0,
    val decoration: String = "000",
//    val differentProviderColor: Int = 0
//    val backgroundColorActive: Int = 0,


    )