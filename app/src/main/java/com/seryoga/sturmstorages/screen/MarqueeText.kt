package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.Font

//@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarqueeText(
    text: String,
    textColor: Color = ColorGreen,

) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.basicMarquee(),
            fontFamily = Font.digitPlay,
            color = textColor,
            text = text
        )
    }
}