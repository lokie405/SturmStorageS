package com.seryoga.sturmstorages.screen.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.ButtonType
import com.seryoga.sturmstorages.screen.ButtonWithIcon

@Composable
fun DialogURL(
    reset: () -> Unit,
    clear: () -> Unit,
    past: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column() {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ButtonWithIcon(
                ButtonType.SMALL,
                R.drawable.reset_icon,
                onClick = reset
            )
            ButtonWithIcon(
                ButtonType.SMALL,
                R.drawable.clear_icon,
                onClick = clear
            )
            ButtonWithIcon(
                ButtonType.SMALL,
                R.drawable.past_icon,
                onClick = past
            )
        }
        content()
    }
}