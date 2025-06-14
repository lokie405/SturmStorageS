package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.ui.theme.Cardboard
import com.seryoga.sturmstorages.ui.theme.Cornsilk
import com.seryoga.sturmstorages.ui.theme.Dollar
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct


@Composable
fun ItemProductProviderHeader(
    settings: SettingData,
    product: Product,
    settingDesign: SettingDesign = SettingDesign(),
    vmProduct: ViewModelProduct
) {

    var backgroundColor by remember { mutableStateOf(Color(settings.colorOfRowBackground)) }
    var isActive by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(
                if (!isActive) {
                    if (settingDesign.name == DesignS.COLOR_OF_ROW_BACKGROUND_ID || settingDesign.name == DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID) {
                        Color(settingDesign.color)
                    } else Color(settings.colorOfRowBackground)
                } else Color(settings.colorOfRowBackgroundActive)
            )
            .clickable {
                isActive = !isActive
            },

    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.6f),
                color = if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    Color(settingDesign.color)
                } else Color(settings.colorOfProduct),
                fontSize = if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    settingDesign.size.sp
                } else settings.fontSizeOfProduct.sp,
                fontFamily = if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    settingDesign.font
                } else Font.mapFontsFamily[settings.fontFamilyOfProduct],
//                fontFamily = Font.jetBrainMonoMedium,
//                fontSize = 14.sp,
//                color = Cornsilk,
                maxLines = 3,
                text = remember(product.name, vmProduct.productsInput) {
                    buildAnnotatedString {
                        val lowerText = product.name.lowercase()
                        var currentIndex = 0

                        while (currentIndex < product.name.length) {
                            val match = vmProduct.productsInput
                                .mapNotNull { word ->
                                    val index = lowerText.indexOf(word.lowercase(), currentIndex)
                                    if (index != -1) index to word else null
                                }
                                .minByOrNull { it.first }

                            if (match != null && match.first >= currentIndex) {
                                val (matchIndex, matchWord) = match
                                append(product.name.substring(currentIndex, matchIndex)) // normal
                                withStyle(
                                    SpanStyle(
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold,

                                    )
                                ) {
                                    append(
                                        product.name.substring(
                                            matchIndex,
                                            matchIndex + matchWord.length
                                        )
                                    ) // highlight
                                }
                                currentIndex = matchIndex + matchWord.length
                            } else {
                                append(product.name.substring(currentIndex))
                                break
                            }
                        }
                    }
                }
//                text = product.name,
            )
//        Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center,


                ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .fillMaxHeight(),
                    textAlign = TextAlign.Center,
                    text = product.quantity.replace(".000", ""),
                    color = if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
                        Color(settingDesign.color)
                    } else {
                        Color(settings.colorOfQuantity)
                    },
                    fontSize = if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
                        settingDesign.size.sp
                    } else settings.fontSizeOfQuantity.sp,
                    fontFamily = if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
                        settingDesign.font
                    } else Font.mapFontsFamily[settings.fontFamilyOfQuantity],
//                    fontFamily = Font.jetBrainMonoMedium,
//                    fontSize = 14.sp,
//                    color = Cardboard
                )
            }
//        Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center

            ) {1
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.End,
                    text = product.price.replace("грн.", if(settings.hryvniaSign) "₴" else ""),
                    color = if (settingDesign.name == (DesignS.PRICE_DESIGN)) {
                        Color(settingDesign.color)
                    } else {
                        Color(settings.colorOfPrice)
                    },
                    fontSize = if (settingDesign.name == DesignS.PRICE_DESIGN) {
                        settingDesign.size.sp
                    } else settings.fontSizeOfPrice.sp,
                    fontFamily = if (settingDesign.name == DesignS.PRICE_DESIGN) {
                        settingDesign.font
                    } else Font.mapFontsFamily[settings.fontFamilyOfPrice],
//                    fontFamily = Font.jetBrainMonoMedium,
//                    fontSize = 14.sp,
//                    color = Dollar
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(5.dp)
//            .background(co)

        )
    }

}


@Preview(showBackground = true)
@Composable
fun Prev() {
//    ItemProductProviderHeader()
}