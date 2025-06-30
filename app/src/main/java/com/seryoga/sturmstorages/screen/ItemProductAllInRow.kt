package com.seryoga.sturmstorages.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.ui.theme.ColorGrey
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.DarkestGrey
import com.seryoga.sturmstorages.ui.theme.UkraineBlue
import com.seryoga.sturmstorages.ui.theme.UkraineYellow
import com.seryoga.sturmstorages.util.Const.TAG
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun ItemProductAllInRow(
    settings: SettingData,
    product: Product,
    colorProvider: Color?,
    settingDesign: SettingDesign = SettingDesign(),
    vmProduct: ViewModelProduct,
) {
    var isActive by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(
                if (!isActive) {
                    if (settingDesign.name == DesignS.BACKGROUND_DESIGN) {
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
                .weight(0.5f)
                .padding(horizontal = 4.dp)
        ) {

//            val startUkraine = product.name.indexOf("і")  //  ukraine
//            val endUkraine = startUkraine + 1
//            var result = product.name
//
//            var annotatedString =
//                    buildAnnotatedString {
//                        if(startUkraine < 0){
//                            append(product.name)
//                        } else {
//
//                            append(product.name)
//                            addStyle(
//                                style = SpanStyle(
//                                    brush = Brush.linearGradient(listOf(UkraineYellow, UkraineBlue)),
////                                    background = Brush.horizontalGradient(listOf(UkraineBlue, UkraineYellow)),
////                                    color = UkraineYellow,
//                                    fontWeight = FontWeight.Bold,
////                                    background = UkraineBlue,
//                                ),
//                                start = startUkraine,
//                                end = endUkraine
//                            )
//                        }
//                        }

//            if(product.name.matches("і".toRegex())){
//                result = annotatedString
//                }

//            var listOfSearch = ViewModelSturm().listOfSearch
//            val startIndex = listOf(0)
//            val endIndex = listOf(0)
//            val annotatedString = buildAnnotatedString {
//                append(product.name)
//            }
//            Log.i(TAG, "IN ITEM : from->${settingDesign.name}; is -> ${settingDesign.font}");
//            Log.i(TAG, "without settingDesign ${settings.fontSizeOfProduct}")
//            val colorHighlights by remember(settingDesign.name, settings.colorOfHighlight) {
//                derivedStateOf {
//                    if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                        Color(settingDesign.color)
//                    } else {
//                        Color(settings.colorOfHighlight)
//                    }
//                }
//            }
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
                text = remember(product.name, vmProduct.productsInput, settingDesign) {
                    buildAnnotatedString {
                        val lowerText = product.name.lowercase()
                        var currentIndex = 0

                        while (currentIndex < product.name.length) {
                            val match = if (settingDesign.name != "") {
                                listOf("cl", "илк", "16")
                            } else {
                                vmProduct.productsInput
                            }
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
                                        color = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
                                            Color(settingDesign.color)
                                        } else { Color(settings.colorOfHighlight) },
                                        fontSize = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
                                            settingDesign.size.sp
                                        } else settings.fontSizeOfHighlight.sp,
                                        fontFamily = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
                                            settingDesign.font
                                        } else Font.mapFontsFamily[settings.fontFamilyOfHighlight],
                                        fontWeight = FontWeight.Bold,
                                        background = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
                                            Color(settingDesign.backgroundColor)
                                        } else { Color(settings.colorOfHighlightBackground) },
                                        textDecoration = TextDecoration.Underline,
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
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(
                        text = product.price.replace("грн.", if (settings.hryvniaSign) "₴" else ""),
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
                color = if (settingDesign.name == (DesignS.PROVIDER_DESIGN) || settingDesign.name == (DesignS.PROVIDER_SECOND_DESIGN)) {
                    Color(settingDesign.color)
                } else {
                    colorProvider!!
                },
                fontSize = if (settingDesign.name == DesignS.PROVIDER_DESIGN || settingDesign.name == DesignS.PROVIDER_SECOND_DESIGN) {
                    settingDesign.size.sp
                } else settings.fontSizeOfProvider.sp,
                fontFamily = if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
                    settingDesign.font
                } else Font.mapFontsFamily[settings.fontFamilyOfProvider],
            )
        }
    }
}