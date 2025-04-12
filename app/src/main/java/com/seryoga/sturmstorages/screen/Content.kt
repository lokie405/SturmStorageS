package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.Const.TAG


@Composable
fun Content(vmProduct: ViewModelProduct) {

    val products by vmProduct.getProduct.collectAsState()
    val s_DisplayType by SettingStoreManager.getDisplayType(LocalContext.current).collectAsState(Const.DISPLAY_TYPE_ALL_IN_ROW)

//    Log.i(TAG, "00000 ----- ------ $s_DisplayType");
    when(s_DisplayType){
        Const.DISPLAY_TYPE_ALL_IN_ROW -> {
            AllInRow(products)
        }
        else -> Log.i(TAG, "WRONG");


    }
//    val colorsOfProvider1: List<Color> = listOf(Const.COLOR_PROVIDER_1, Const.COLOR_PROVIDER_MAIN)
//    val colorsOfProvider2: List<Color> = listOf(Const.COLOR_PROVIDER_2, Const.COLOR_PROVIDER_MAIN)



//    var mapOfProvidersColor = remember { mutableStateMapOf<String, Color>() }
//    var isOddProvider by remember {mutableStateOf(false)}
//    val brush1 = remember { Brush.linearGradient(colors = colorsOfProvider1) }
//    val brush2 = remember { Brush.linearGradient(colors = colorsOfProvider2) }
//    var currentProvider = ""

}

@Composable
fun AllInRow(products: List<Product>) {
    val colorsOfProvider = listOf(Const.COLOR_PROVIDER_1, Const.COLOR_PROVIDER_2)
    val providerColorMap = remember(products){
        val map = mutableMapOf<String, Int>()
        var colorIndex = 0
        for (product in products) {
            if(product.provider !in map){
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
            ItemProduct(item, colorsOfProvider[providerColorMap[item.provider] ?:0 ])
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 4.dp)
                    .background(color = colorsOfProvider[providerColorMap[item.provider] ?: 0]!!),

                )
        }
    }
}