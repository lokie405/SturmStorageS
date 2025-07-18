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
import androidx.compose.runtime.collectAsState
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
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct

@Composable
fun ItemProductAllInRowAtCell(
//    settings: SettingData,
    product: Product,
    colorProvider: Color?,
//    settingDesign: SettingDesign = SettingDesign(),
    vmProduct: ViewModelProduct,
) {
//    var backgroundColor by remember { mutableStateOf(DarkestGrey) }
    val allSettings by vmProduct.allSettings.collectAsState()
    var isActive by remember { mutableStateOf(false) }
    val itemOfDesign = vmProduct.itemToDesign.collectAsState()

//    Log.i(TAG, "settingDesign.name = ${settingDesign.name}");
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                if (!isActive) {
                    if (itemOfDesign.value == DesignS.COLOR_OF_ROW_BACKGROUND_ID || itemOfDesign.value == DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID) {
//                        note: settingDesign.color
                        Color(allSettings.colorOfRowBackground)
                    } else Color(allSettings.colorOfRowBackground)
                } else Color(allSettings.colorOfRowBackgroundActive)
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
                color = Color(allSettings.colorOfProduct),
                fontSize = allSettings.fontSizeOfProduct.sp,
                fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfProduct],

                text = remember(product.name, vmProduct.productsInput) {
                    buildAnnotatedString {
                        val lowerText = product.name.lowercase()
                        var currentIndex = 0

                        while (currentIndex < product.name.length) {
                            val match = if (vmProduct.itemToDesign.value != DesignS.NO_DESIGN) {
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
//                                Log.i("MyLog", "sss: ${settingDesign.decoration}");
                                val (matchIndex, matchWord) = match
                                append(product.name.substring(currentIndex, matchIndex)) // normal
                                withStyle(
                                    SpanStyle(
                                        color = Color(allSettings.colorOfHighlight),
                                        fontSize = allSettings.fontSizeOfHighlight.sp,
                                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfHighlight],
                                        fontWeight = FontWeight.Bold,
                                        background = Color(allSettings.colorOfHighlightBackground)
//                                        textDecoration = TextDecoration.Underline,
                                    )
                                ) {
                                    append(
                                        product.name.substring(
                                            matchIndex,
                                            matchIndex + matchWord.length
                                        )
                                    )
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
                        color = Color(allSettings.colorOfQuantity),
                        fontSize = allSettings.fontSizeOfQuantity.sp,
                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfQuantity],
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = product.price.replace("грн.", if (allSettings.hryvniaSign) "₴" else ""),
                        color = Color(allSettings.colorOfPrice),
                        fontSize = allSettings.fontSizeOfPrice.sp,
                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfPrice],
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
                color = colorProvider!!,
                fontSize = allSettings.fontSizeOfProvider.sp,
                fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfProvider],
            )
        }
    }
}