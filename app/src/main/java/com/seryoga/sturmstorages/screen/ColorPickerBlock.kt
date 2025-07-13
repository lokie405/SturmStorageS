package com.seryoga.sturmstorages.screen

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorYellow

@Composable
fun ColorPickerBlock(
    modifier: Modifier = Modifier,
    initialColor: Color,
    controller: ColorPickerController,
    onColorChange: (Color) -> Unit,
    isTransparentDisplay: Boolean = false,
) {
    Log.i("MyLog", "Initial color: ${initialColor}");
    val hexColor = remember { mutableStateOf(initialColor.toHex()) }
    Column(modifier = modifier.animateContentSize()) {
        SpacerS(20)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            var isTransparent by remember { mutableStateOf(false) }
            if (isTransparentDisplay) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 20.dp, top = 20.dp),
                    onClick = {
                        if (!isTransparent) {
                            onColorChange(Color.Transparent)
                            isTransparent = true
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.gradient_icon),
                        contentDescription = "Transparent",
                        tint = if(isTransparent) MaterialTheme.colorScheme.onTertiary
                            else MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            HsvColorPicker(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                initialColor = initialColor,
                controller = controller,
                onColorChanged = { envelope ->
                    Log.i("MyLog", "onColorChange in ColorBlock: ${envelope.color.toHex()}");
                    hexColor.value = envelope.color.toHex()
                    onColorChange(envelope.color)
                    isTransparent = false
                }
            )
        }

        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .height(35.dp)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.onPrimary,
                    RoundedCornerShape(6.dp)
                ),
            initialColor = initialColor,
            controller = controller
        )

        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .height(35.dp)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.onPrimary,
                    RoundedCornerShape(6.dp)
                ),
            initialColor = initialColor,
            controller = controller
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .width(100.dp)
                    .padding(top = 10.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .border(
                        1.dp,
                        invertColor(initialColor),
                        RoundedCornerShape(6.dp)
                    ),
                controller = controller,
            )
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 10.dp, start = 20.dp),
                text = hexColor.value,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        SpacerS(20)
    }
}