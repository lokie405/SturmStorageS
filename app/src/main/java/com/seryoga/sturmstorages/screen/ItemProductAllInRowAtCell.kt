package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.ColorGrey
import com.seryoga.sturmstorages.ui.theme.ColorLightGrey
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.DarkestGrey

@Composable
fun ItemProductAllInRowAtCell(settings: SettingData, item: Product, colorProvider: Color?) {
    var backgroundColor by remember {mutableStateOf(DarkestGrey)}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(backgroundColor)
            .clickable(onClick = {
                backgroundColor = ColorGrey
            }),
    ) {
        Box(
            modifier = Modifier
                .weight(0.6f)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = item.name,
                color = ColorLightGrey,
                fontSize = 13.sp,
                fontFamily = Font.jetBrainMonoBold

            )
        }
        Box(
            modifier = Modifier
                .weight(0.2f)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = item.quantity.replace(".000", ""),
                        fontSize = 12.sp,
                        color = ColorGreen,
                        fontFamily = Font.jetBrainMonoBold
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = item.price.replace("грн.", if(settings.hryvniaSign) "₴" else ""),
                        fontSize = 11.sp,
                        color = Color.Yellow,
                        fontFamily = Font.jetBrainMonoMedium
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(0.3f)
                .align(Alignment.CenterVertically)
                .padding(horizontal = 4.dp),
        ) {
            Text(
                text = item.provider,
                fontSize = 11.sp,
                color = colorProvider!!,
                fontFamily = Font.jetBrainMonoMedium
            )
        }
    }
}