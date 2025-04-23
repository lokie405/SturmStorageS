package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.Wheat
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.Const


@SuppressLint("SuspiciousIndentation")
@Composable
fun Content(
    vmProduct: ViewModelProduct,
    settingStoreManager: SettingStoreManager = SettingStoreManager(LocalContext.current),
) {

    val settings by settingStoreManager.settingsFlow.collectAsState(SettingData())
//    val displayType by settingStoreManager.getDisplayType().collectAsState(DisplayType.ALL_IN_ROW)
    val products by vmProduct.getProduct.collectAsState()
//    val listOfProviders by remember { mutableStateOf(vmProduct.providers) }

//    val s_DisplayType by settingManager.getDisplayType(LocalContext.current)
//        .collectAsState(Const.DISPLAY_TYPE_ALL_IN_ROW)

//    Log.i(TAG, "00000 ----- ------ $s_DisplayType");
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
fun AllInRow(settings: SettingData, products: List<Product>) {
    val colorsOfProvider = listOf(Color(settings.colorOfProvider), Color(settings.colorOfProviderSecond))
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
            ItemProductAllInRow(settings, item, colorsOfProvider[providerColorMap[item.provider] ?: 0])
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
fun AllInRowAtCell(settings: SettingData, products: List<Product>) {
    val colorsOfProvider = listOf(Const.COLOR_PROVIDER_1, Const.COLOR_PROVIDER_2)
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
//                        .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {


                ItemProductAllInRowAtCell(
                    settings,
                    item,
                    colorsOfProvider[providerColorMap[item.provider] ?: 0]
                )
            }
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(10.dp)
////                    .padding(horizontal = 4.dp)
////                    .background(color = colorsOfProvider[providerColorMap[item.provider] ?: 0]),
//
//                )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProviderHeader(settings: SettingData, groupByProducts: Map<String, List<Product>>) {

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
//                        .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(8.dp)),

                    ) {

                    Text(
                        modifier = Modifier
                            .background(Wheat)
                            .fillMaxWidth()

                            .padding(vertical = 10.dp),
                        textAlign = TextAlign.Center,
                        text = provider,
                        fontFamily = Font.jetBrainMonoBold,
                        color = MaterialTheme.colorScheme.primary
                    )
//                        Spacer(modifier = Modifier
//                            .height(10.dp)
//                            .fillMaxWidth()
//                            .background(MaterialTheme.colorScheme.background))

                }

            }
            items(products) { product ->
                ItemProductProviderHeader(settings, product)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
//        items(products) { product ->
//            ItemProductProviderHeader(product)
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(10.dp)
////                    .padding(horizontal = 4.dp)
//
//                )
//        }
    }
}

