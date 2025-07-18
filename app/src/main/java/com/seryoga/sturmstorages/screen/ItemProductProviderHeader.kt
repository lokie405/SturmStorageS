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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct


@Composable
fun ItemProductProviderHeader(
//    settings: SettingData,
    product: Product,
//    settingDesign: SettingDesign = SettingDesign(),
    vmProduct: ViewModelProduct,
) {

    val allSettings by vmProduct.allSettings.collectAsState()
    var isActive by remember { mutableStateOf(false) }
    val itemToDesign = vmProduct.itemToDesign.collectAsState()

//    val backgroundColor = C
    val backgroundColor by remember(allSettings) {
        derivedStateOf { Color(allSettings.colorOfRowBackground) }
    }
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
                    if (itemToDesign.value == DesignS.COLOR_OF_ROW_BACKGROUND_ID || itemToDesign.value == DesignS.COLOR_OF_ROW_BACKGROUND_ACTIVE_ID) {
                        Color(allSettings.colorOfRowBackground)
                    } else Color(allSettings.colorOfRowBackground)
                } else Color(allSettings.colorOfRowBackgroundActive)
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
                color = Color(allSettings.colorOfProduct),
                fontSize = allSettings.fontSizeOfProduct.sp,
                fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfProduct],
                maxLines = 3,
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
                                        fontSize = allSettings.fontSizeOfHighlight.sp,
                                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfHighlight],
                                        fontWeight = FontWeight.Bold,
                                        background = Color(allSettings.colorOfHighlightBackground),
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
                    color = Color(allSettings.colorOfQuantity),
                    fontSize = allSettings.fontSizeOfQuantity.sp,
                    fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfQuantity],
//                    fontFamily = Font.jetBrainMonoMedium,
//                    fontSize = 14.sp,
//                    color = Cardboard
                )
            }
//        Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center

            ) {
                1
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.End,
                    text = product.price.replace(
                        "грн.",
                        if (allSettings.hryvniaSign) "₴" else ""
                    ),
                    color = Color(allSettings.colorOfPrice),
                    fontSize = allSettings.fontSizeOfPrice.sp,
                    fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfPrice],
//                    fontFamily = Font.jetBrainMonoMedium,
//                    fontSize = 14.sp,
//                    color = Dollar
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(5.dp)

        )
    }

}
