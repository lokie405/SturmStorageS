package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.LoadStatus
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.model.SettingDesign
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.web.LoadProducts
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.log


//@SuppressLint("SuspiciousIndentation")
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Content(
    vmProduct: ViewModelProduct,
    settingStoreManager: SettingStoreManager = SettingStoreManager(LocalContext.current),
) {

    val settings by settingStoreManager.settingsFlow.collectAsState(SettingData())
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current




//    val progress by remember { mutableStateOf(0f) }

//    val displayType by settingStoreManager.getDisplayType().collectAsState(DisplayType.ALL_IN_ROW)

//    val products by vmProduct.getProduct.collectAsState()/*right*/
    val products by vmProduct.products.collectAsState()

//        vmProduct.loadProducts(listOf("ку", "мул"), "%УЗП - Ручний iнструмент%")
    vmProduct.displayProducts()

//    LaunchedEffect(Unit) {
//    }
//    val prod by

//    val listOfProviders by remember { mutableStateOf(vmProduct.providers) }

//    val s_DisplayType by settingManager.getDisplayType(LocalContext.current)
//        .collectAsState(Const.DISPLAY_TYPE_ALL_IN_ROW)

//    Log.i(TAG, "00000 ----- ------ ${settings.colorOfProviderBackground}");
//    when (s_DisplayType) {
//        Const.DISPLAY_TYPE_ALL_IN_ROW -> {

    when (settings.displayType) {
        DisplayType.ALL_IN_ROW -> AllInRow(settings, products)
        DisplayType.ALL_IN_ROW_AT_CELL -> AllInRowAtCell(settings, products)
        DisplayType.PROVIDER_HEADER -> {
            val groupByProviders: Map<String, List<Product>> = products.groupBy { it.provider }
            ProviderHeader(settings, groupByProviders)
        }
    }

}

@Composable
fun AllInRow(
    settings: SettingData,
    products: List<Product>,
    settingDesign: SettingDesign = SettingDesign(),
) {
    val colorsOfProvider = listOf(
        if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
            Color(settingDesign.color)
        } else Color(settings.colorOfProvider),
        if (settingDesign.name == DesignS.PROVIDER_SECOND_DESIGN) {
            Color(settingDesign.color)
        } else Color(settings.colorOfProviderSecond),
    )
    val providerColorMap = remember(products) {
        val map = mutableMapOf<String, Int>()
        var colorIndex = 0
        for (product in products) {
            if (product.provider !in map) {
                map[product.provider] = colorIndex
                colorIndex = (colorIndex + 1) % colorsOfProvider.size
            }
        }
        map
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(products) { item ->
            ItemProductAllInRow(
                settings,
                item,
                colorsOfProvider[providerColorMap[item.provider] ?: 0],
                settingDesign,
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 4.dp)
                    .background(color = colorsOfProvider[providerColorMap[item.provider] ?: 0]),

                )
        }
    }
}

@Composable
fun AllInRowAtCell(
    settings: SettingData,
    products: List<Product>,
    settingDesign: SettingDesign = SettingDesign(),
) {
    val colorsOfProvider = listOf(
        if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
            Color(settingDesign.color)
        } else Color(settings.colorOfProvider),
        if (settingDesign.name == DesignS.PROVIDER_SECOND_DESIGN) {
            Color(settingDesign.color)
        } else Color(settings.colorOfProviderSecond),
    )
//    Log.i(TAG, "root.name: ${settingDesign.name}; colorofProvider: ${colorsOfProvider}");
    val providerColorMap = remember(products) {
        val map = mutableMapOf<String, Int>()
        var colorIndex = 0
        for (product in products) {
            if (product.provider !in map) {
                map[product.provider] = colorIndex
                colorIndex = (colorIndex + 1) % colorsOfProvider.size
            }
        }
        map
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(products) { item ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        color = colorsOfProvider[providerColorMap[item.provider] ?: 0],
                        width = 2.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
            ) {


                ItemProductAllInRowAtCell(
                    settings,
                    item,
                    colorsOfProvider[providerColorMap[item.provider] ?: 0],
                    settingDesign
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProviderHeader(
    settings: SettingData,
    groupByProducts: Map<String, List<Product>>,
    settingDesign: SettingDesign = SettingDesign(),
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        groupByProducts.forEach { (provider, products) ->

            stickyHeader {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(8.dp)),

                    ) {

                    Text(
                        modifier = Modifier
                            .background(
                                if (settingDesign.name == DesignS.COLOR_OF_PROVIDER_BACKGROUND_ID) {
                                    Color(settingDesign.color)
                                } else Color(settings.colorOfProviderBackground)
                            )
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        text = provider,
                        textAlign = TextAlign.Center,
//                        color = if (root == DesignS.PROVIDER_DESIGN) {
//                            Color(currentColor.toArgb())
//                        } else {
//                            Color(settings.colorOfProvider)
//                        },
//                        fontSize = if (root == DesignS.PROVIDER_DESIGN) {
//                            currentFontSize.sp
//                        } else settings.fontSizeOfProvider.sp,
//                        fontFamily = if (root == DesignS.PROVIDER_DESIGN) {
//                            Font.mapFontsFamily[currentFontFamily]
//                        } else Font.mapFontsFamily[settings.fontFamilyOfProvider],
                        color = if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
                            Color(settingDesign.color)
                        } else Color(settings.colorOfProvider),
                        fontSize = if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
                            settingDesign.size.sp
                        } else settings.fontSizeOfProvider.sp,
                        fontFamily = if (settingDesign.name == DesignS.PROVIDER_DESIGN) {
                            settingDesign.font
                        } else Font.mapFontsFamily[settings.fontFamilyOfProvider],
//                        color = Color(settings.colorOfProvider),
//                        fontSize = settings.fontSizeOfProvider.sp,
//                        fontFamily = Font.mapFontsFamily[settings.fontFamilyOfProvider],
                    )
                }
            }
            items(products) { product ->
                ItemProductProviderHeader(
                    settings,
                    product,
                    settingDesign
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

