package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.ui.theme.ColorGrey
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.DarkestGrey

@Composable
fun ItemProductAllInRow(
    settings: SettingData,
    product: Product,
    colorProvider: Color?,
    settingDesign: SettingDesign = SettingDesign(),
) {

    var backgroundColor by remember { mutableStateOf(DarkestGrey) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(backgroundColor)
            .clickable(onClick = {
                backgroundColor = ColorGrey
            }),
    ) {
        Box(
            modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = product.name,
//                NOTE: this is for number highlight
//
                color = if (settingDesign.name == (DesignS.COLOR_OF_PRODUCT_ID)) {
                    Color(settingDesign.color)
                } else {
                    Color(settings.colorOfProduct)
                },
                fontSize = settings.fontSizeOfProduct.sp,
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
//                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Text(
                        text = product.quantity.replace(".000", ""),
                        fontSize = settings.fontSizeOfQuantity.sp,
                        color = if (settingDesign.name == DesignS.COLOR_OF_QUANTITY_ID) {
                            Color(settingDesign.color)
                        } else {
                            Color(settings.colorOfQuantity)
                        },
                        fontFamily = Font.jetBrainMonoBold
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(
                        text = product.price.replace("грн.", if (settings.hryvniaSign) "₴" else ""),
                        fontSize = settings.fontSizeOfPrice.sp,
                        color = if(settingDesign.name == (DesignS.COLOR_OF_PRICE_ID)){
                            Color(settingDesign.color)
                        } else {
                            Color(settings.colorOfPrice)
                        },
                        fontFamily = Font.jetBrainMonoMedium
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(0.3f)
                .align(Alignment.CenterVertically)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = product.provider,
                fontSize = settings.fontSizeOfProvider.sp,
                color = if(settingDesign.name == (DesignS.COLOR_OF_PROVIDER_ID)){
                    Color(settingDesign.color)
                } else if(settingDesign.name == (DesignS.COLOR_OF_PROVIDER_SECOND_ID)){
                    Color(settingDesign.color)
                } else {
                    colorProvider!!
                },
                fontFamily = Font.jetBrainMonoMedium
            )
        }
    }
}