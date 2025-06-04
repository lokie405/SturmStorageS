package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SpriteAnimation(
    icons: List<ImageVector>,
    frameDuration: Long = 300L,
    size: Int = 24,
    tint: Color =  MaterialTheme.colorScheme.onPrimary

) {
    var currentFrame by remember{mutableStateOf(0)}

    LaunchedEffect(Unit){
        while(true){
            delay(frameDuration)
            currentFrame = (currentFrame + 1) % icons.size
        }
    }

    Icon(
        imageVector = icons[currentFrame],
        contentDescription = null,
        modifier = Modifier.size(size.dp),
        tint = tint
    )
}

