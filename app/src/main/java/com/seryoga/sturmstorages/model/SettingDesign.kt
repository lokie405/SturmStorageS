package com.seryoga.sturmstorages.model

import androidx.compose.ui.text.font.FontFamily
import com.seryoga.sturmstorages.ui.theme.Font

/**
 * For set parameter inside Row Items represent
 * */
data class SettingDesign(
    val name: String = "",
    val color: Int = -16711681,
    val size: Int = 0,
    val font: FontFamily? = Font.mapFontsFamily[Font.JET_BRAIN],
    val backgroundColor: Int = 0,
    val decoration: Boolean = false,
//    val differentProviderColor: Int = 0
//    val backgroundColorActive: Int = 0,


    )