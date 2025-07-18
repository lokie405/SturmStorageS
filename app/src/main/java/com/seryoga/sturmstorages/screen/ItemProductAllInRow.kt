package com.seryoga.sturmstorages.screen

import SettingStoreManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct

@Composable
fun ItemProductAllInRow(
//    settings: SettingData,
    product: Product,
    colorProvider: Color?,
//    settingDesign: SettingDesign = SettingDesign(),
    vmProduct: ViewModelProduct,
) {
    val settings = SettingStoreManager(LocalContext.current)
    val allSettings by vmProduct.allSettings.collectAsState()
    val itemToDesign = vmProduct.itemToDesign.collectAsState()
//    val sColorOfProduct = StateFlow<Int> = settings.getColorOfProduct()
//    Log.i("MyLog", "[start]ItemProductAllRow -> ${Color(settings.colorOfProduct).toHex()}");
    var NAME = "llll"
//    val settingStoreManager = SettingStoreManager(LocalContext.current)
    var isActive by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(
                if (!isActive) {
                    Color(allSettings.colorOfRowBackground)
//                    if (settingDesign.name == DesignS.BACKGROUND_DESIGN) {
//                        note: settingDesign.color
                } else {
                    Color(allSettings.colorOfRowBackgroundActive)
                }
//                } else Color(settings.colorOfRowBackgroundActive)
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
//            Log.i("MyLog", "THIS");
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
//            Log.i("MyLog", "From AllInRow color = ${settings.colorOfProduct}")
            Text(
//                color = if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
////                    Color(settings.color)
//                    Color(settings.colorOfProduct)
//                } else Color(settings.colorOfProduct),
                color = Color(allSettings.colorOfProduct),
                fontSize = allSettings.fontSizeOfProduct.sp,
                fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfProduct],
                /*if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    settingDesign.size.sp
                } else */
                /*if (settingDesign.name == DesignS.PRODUCT_DESIGN) {
                    settingDesign.font
                } else */


//                text = remember(product.name, vmProduct.productsInput, settingDesign) {
//                    buildAnnotatedString {
//                        val lowerText = product.name.lowercase()
//                        var currentIndex = 0
//
//                        while (currentIndex < product.name.length) {
//                            val match = if (settingDesign.name != "") {
//                                listOf("cl", "илк", "16")
//                            } else {
//                                vmProduct.productsInput
//                            }
//                                .mapNotNull { word ->
//                                    val index = lowerText.indexOf(word.lowercase(), currentIndex)
//                                    if (index != -1) index to word else null
//                                }
//                                .minByOrNull { it.first }
//
//                            if (match != null && match.first >= currentIndex) {
//
//                                val (matchIndex, matchWord) = match
//                                append(product.name.substring(currentIndex, matchIndex)) // normal
//                                withStyle(
//                                    SpanStyle(
//                                        color = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            Color(settingDesign.color)
//                                        } else { Color(settings.colorOfHighlight) },
//                                        fontSize = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            settingDesign.size.sp
//                                        } else settings.fontSizeOfHighlight.sp,
//                                        fontFamily = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            settingDesign.font
//                                        } else Font.mapFontsFamily[settings.fontFamilyOfHighlight],
//                                        fontWeight = FontWeight.Bold,
//                                        background = if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            Color(settingDesign.backgroundColor)
//                                        } else { Color(settings.colorOfHighlightBackground) },
//                                        textDecoration = TextDecoration.Underline,
//                                    )
//                                ) {
//                                    append(
//                                        product.name.substring(
//                                            matchIndex,
//                                            matchIndex + matchWord.length
//                                        )
//                                    ) // highlight
//                                }
//                                currentIndex = matchIndex + matchWord.length
//                            } else {
//                                append(product.name.substring(currentIndex))
//                                break
//                            }
//                        }
//                    }
//                }
//            )


                text = remember(product.name, vmProduct.productsInput) {
                    buildAnnotatedString {
                        val lowerText = product.name.lowercase()
                        var currentIndex = 0

                        while (currentIndex < product.name.length) {
                            val match = if (itemToDesign.value != DesignS.NO_DESIGN) {
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
                                        color = Color(allSettings.colorOfHighlight),
//                                        if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            Color(settingDesign.color)
//                                        } else { Color(settings.colorOfHighlight) },
                                        fontSize = allSettings.fontSizeOfHighlight.sp,
//                                        if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            settingDesign.size.sp
//                                        } else
                                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfHighlight],
//                                        if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            settingDesign.font
//                                        } else

//                                        todo: font weight
//                                        fontWeight = if(settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            if(settingDesign.decoration.toString().toCharArray()[0] == '2') {
//                                                FontWeight.Bold
//                                            } else FontWeight.Normal
//                                        } else FontWeight.Bold,
//                                        todo: end
                                        background = Color(allSettings.colorOfHighlightBackground),
//                                        if (settingDesign.name == DesignS.HIGHLIGHT_DESIGN) {
//                                            Color(settingDesign.backgroundColor)
//                                        } else { Color(settings.colorOfHighlightBackground) },
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
                        color = Color(allSettings.colorOfQuantity),
//                        if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
//                            Color(settingDesign.color)
//                        } else {
//                        },
                        fontSize = allSettings.fontSizeOfQuantity.sp,
//                        if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
//                            settingDesign.size.sp
//                        } else
                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfQuantity],
//                        if (settingDesign.name == DesignS.QUANTITY_DESIGN) {
//                            settingDesign.font
//                        } else
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(
                        text = product.price.replace("грн.", if (true) "₴" else ""),
                        color = Color(allSettings.colorOfPrice),
//                        if (settingDesign.name == (DesignS.PRICE_DESIGN)) {
//                            Color(settingDesign.color)
//                        } else {
//                        },
                        fontSize = allSettings.fontSizeOfPrice.sp,
//                        if (settingDesign.name == DesignS.PRICE_DESIGN) {
//                            settingDesign.size.sp
//                        } else
                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfPrice],
//                        if (settingDesign.name == DesignS.PRICE_DESIGN) {
//                            settingDesign.font
//                        } else
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
                color = colorProvider!!,
//                if (settingDesign.name == (DesignS.PROVIDER_DESIGN) || settingDesign.name == (DesignS.PROVIDER_SECOND_DESIGN)) {
//                    Color(settingDesign.color)
//                } else {
//                    colorProvider!!
//                },
                fontSize = allSettings.fontSizeOfProvider.sp,
//                if (settingDesign.name == DesignS.PROVIDER_DESIGN || settingDesign.name == DesignS.PROVIDER_SECOND_DESIGN) {
//                    settingDesign.size.sp
//                } else
                fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfProvider],
//                if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
//                    settingDesign.font
//                } else
            )
        }
    }
//    LaunchedEffect(Unit) {
//
//        settingStoreManager.deleteAllPreferences()
//    }
}
//    Log.i("MyLog", "[start]ItemProductAllRow -> ${Color(settings.colorOfProduct).toHex()}");
