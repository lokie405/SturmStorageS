package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.ui.theme.Font

@Composable
fun ScreenTitleText(
    title: String,
//    modifier: Modifier = Modifier,
//    onClick: () -> Unit = {}
) {
    Text(
//        modifier = if(modifier == Modifier) Modifier.fillMaxWidth() else modifier,
        modifier = Modifier.fillMaxWidth(),
        text = title,
        fontSize = 28.sp,
        fontFamily = Font.jetBrainMonoBold,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onPrimary,

    )
}