package com.seryoga.sturmstorages.screen

import SettingStoreManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.ButtonType
import com.seryoga.sturmstorages.model.RootS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ButtonInDesign(
    type: ButtonType,
    iconResource: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    var buttonWidth = when(type){
        ButtonType.SMALL -> 35.dp
        ButtonType.MEDIUM -> 60.dp
    }
    IconButton(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .width(buttonWidth)
            .height(35.dp)
            .background(MaterialTheme.colorScheme.secondary),
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(iconResource),
                contentDescription = stringResource(R.string.cancel),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}