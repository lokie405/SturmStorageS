package com.seryoga.sturmstorages.screen

import android.util.Log
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.ColorGrey
import com.seryoga.sturmstorages.ui.theme.ColorLightGrey
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.DarkestGrey
import com.seryoga.sturmstorages.util.Const.TAG
import com.seryoga.sturmstorages.util.ViewModelProduct

@Composable
fun ItemProductAllInRowAtCell(
    settings: SettingData,
    product: Product,
    colorProvider: Color?,
    settingDesign: SettingDesign = SettingDesign(),
    vmProduct: ViewModelProduct,
) {
    var backgroundColor by remember { mutableStateOf(DarkestGrey) }
    var isActive by remember { mutableStateOf(false) }

//    Log.i(TAG, "settingDesign.name = ${settingDesign.name}");
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                if (!isActive) {
                    if (settingDesign.name == DesignS.COLOR_OF_ROW_BACKGROUND_ID || settingDesign.name == DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID) {
//                        note: settingDesign.color
                        Color(settingDesign.backgroundColor)
                    } else Color(settings.colorOfRowBackground)
                } else Color(settings.colorOfRowBackgroundActive)
            )
            .clickable(onClick = {
                isActive = !isActive
            }),
    ) {
        Box(
            modifier = Modifier
                .weight(0.6f)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                color = if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    Color(settingDesign.color)
                } else Color(settings.colorOfProduct),
                fontSize = if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    settingDesign.size.sp
                } else settings.fontSizeOfProduct.sp,
                fontFamily = if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    settingDesign.font
                } else Font.mapFontsFamily[settings.fontFamilyOfProduct],

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
                                        fontWeight = FontWeight.Bold
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

//                text = highlightWordsInText(product.name, vmProduct.productsInput),

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
                        text = product.quantity.replace(".000", ""),
                        color = if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
                            Color(settingDesign.color)
                        } else Color(settings.colorOfQuantity),
                        fontSize = if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
                            settingDesign.size.sp
                        } else settings.fontSizeOfQuantity.sp,
                        fontFamily = if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
                            settingDesign.font
                        } else Font.mapFontsFamily[settings.fontFamilyOfQuantity],
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = product.price.replace("грн.", if (settings.hryvniaSign) "₴" else ""),
                        color = if (settingDesign.name == DesignS.PRICE_DESIGN) {
                            Color(settingDesign.color)
                        } else Color(settings.colorOfPrice),
                        fontSize = if (settingDesign.name == DesignS.PRICE_DESIGN) {
                            settingDesign.size.sp
                        } else settings.fontSizeOfPrice.sp,
                        fontFamily = if (settingDesign.name == DesignS.PRICE_DESIGN) {
                            settingDesign.font
                        } else Font.mapFontsFamily[settings.fontFamilyOfPrice],
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
                text = product.provider,
                color = if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
                    Color(settingDesign.color)
                } else colorProvider!!,
                fontSize = if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
                    settingDesign.size.sp
                } else settings.fontSizeOfProvider.sp,
                fontFamily = if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
                    settingDesign.font
                } else Font.mapFontsFamily[settings.fontFamilyOfProvider],
            )
        }
    }
}