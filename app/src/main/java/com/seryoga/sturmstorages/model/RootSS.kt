package com.seryoga.sturmstorages.model

import androidx.compose.ui.graphics.Color
import com.seryoga.sturmstorages.ui.theme.ColorLightGrey
import com.seryoga.sturmstorages.ui.theme.Font

class RootSS(name: String) {
    var title: Int = DesignS.titleAndIcons[name] as Int
    var oidColor = Color.Transparent
    var oldFontSize = 0
    var oldFontFamily = Font.JET_BRAIN
    var oldProviderBackground = ColorLightGrey
}