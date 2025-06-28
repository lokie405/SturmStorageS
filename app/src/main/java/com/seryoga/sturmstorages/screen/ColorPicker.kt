package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.seryoga.sturmstorages.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyListScope.ColorPicker(
    titleResource: Int,
    currentColor: Color,
    controller: ColorPickerController,
    hexOfCurrentColor: String,
    onColorChange: (colorEnvelope: ColorEnvelope) -> Unit
) {
    stickyHeader {
        DesignTitle(stringResource(titleResource))
    }
    item {
        SpacerS(20)
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            HsvColorPicker(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(
                        top = 0.dp,
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    ),
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    onColorChange(colorEnvelope)
//
//                    currentColor = colorEnvelope.color // ARGB color value.
//                    scope.launch {
//                        settingStoreManager.saveColor(
//                            decorateItem,
//                            currentColor.toArgb()
//                        )
//
//                    }
//                    hexOfCurrentColor = currentColor.toHex()
                },
                controller = controller,
                initialColor = currentColor
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
            initialColor = currentColor,
            controller = controller,

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
            initialColor = currentColor,
            controller = controller,
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
                        invertColor(currentColor),
                        RoundedCornerShape(6.dp)
                    ),
                controller = controller,
            )
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 10.dp, start = 20.dp),
//                                    textAlign = TextAlign.Center,
                text = hexOfCurrentColor,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        SpacerS(20)

    }


}