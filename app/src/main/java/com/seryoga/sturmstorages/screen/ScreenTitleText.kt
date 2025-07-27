package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const

@Composable
fun ScreenTitleText(
    title: String,
    fontSize: Int = 28,
) {
    Text(
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(end = Const.ICON_SIZE),
        text = title,
        fontSize = fontSize.sp,
        fontFamily = Font.jetBrainMonoBold,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}

