package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.ui.theme.ColorYellow
import com.seryoga.sturmstorages.ui.theme.Font

@Composable
fun IconInListClicked(
    list: List<Int>,
    isSelected: Boolean,
    onClick: () -> Unit,
){
    IconButton (
        onClick = onClick,
    ){
        Icon(
            modifier = Modifier
                .size(30.dp),
            painter = painterResource(list[1]),
            tint = if(isSelected) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onPrimary,
            contentDescription = stringResource(list[0])
        )

    }
}

