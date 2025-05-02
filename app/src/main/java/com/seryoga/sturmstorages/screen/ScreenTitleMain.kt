package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const

@Composable
fun ScreenTitleMain(
    title: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 10.dp, top = Const.TOP_BAR_HEIGHT)
    ) {
        IconButton(
            onClick = {
                onClick()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = stringResource(R.string.back_button),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            fontSize = 28.sp,
            fontFamily = Font.jetBrainMonoBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}