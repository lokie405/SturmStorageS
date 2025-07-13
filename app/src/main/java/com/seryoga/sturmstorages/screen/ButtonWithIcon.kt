package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.ButtonType

@Composable
fun ButtonWithIcon(
    type: ButtonType,
    iconResource: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary

    ) {
    val buttonWidth = when (type) {
        ButtonType.SMALL -> 35.dp
        ButtonType.MEDIUM -> 60.dp
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .width(buttonWidth)
            .height(35.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                onClick()
            },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconResource),
                contentDescription = stringResource(R.string.cancel),
                tint = tint
            )
        }
    }