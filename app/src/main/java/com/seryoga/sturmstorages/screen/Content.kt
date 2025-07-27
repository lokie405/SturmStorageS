package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.model.DisplayType
import com.seryoga.sturmstorages.model.LoadState
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.ui.theme.ColorBlue
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct
import kotlinx.coroutines.delay


//@SuppressLint("SuspiciousIndentation")
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Content(
    vmProduct: ViewModelProduct,
    settingStoreManager: SettingStoreManager = SettingStoreManager(LocalContext.current),
) {
    val settings by settingStoreManager.settingsFlow.collectAsState(SettingData())
    Log.i("MyLog", "___[start]Content -> ${settings.colorOfProduct}");
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


//    val progress by remember { mutableStateOf(0f) }

//    val displayType by settingStoreManager.getDisplayType().collectAsState(DisplayType.ALL_IN_ROW)

//    val products by vmProduct.getProduct.collectAsState()/*right*/
    val products by vmProduct.products.collectAsState()
    val state by vmProduct.state.collectAsState()


    vmProduct.displayProducts()
    if (products.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Wait until fulfilled")
        }
    } else {

        when (settings.displayType) {
            DisplayType.ALL_IN_ROW -> AllInRow(products, vmProduct = vmProduct)
            DisplayType.ALL_IN_ROW_AT_CELL -> AllInRowAtCell(products, vmProduct = vmProduct)
            DisplayType.PROVIDER_HEADER -> {
                val groupByProviders: Map<String, List<Product>> = products.groupBy { it.provider }
                ProviderHeader(groupByProviders, vmProduct = vmProduct)
            }
        }
//}
    }

}

//    Log.i("MyLog", "___[start]Content -> ${settings.colorOfProduct}");
@Composable
fun AllInRow(
    products: List<Product>,
    vmProduct: ViewModelProduct,
) {
    val allSettings by vmProduct.allSettings.collectAsState()
    val itemsToDesign by vmProduct.itemToDesign.collectAsState()
    val colorsOfProvider = listOf(Color(allSettings.colorOfProvider), Color(allSettings.colorOfProviderSecond))
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

    /**
     *  Switch color at provider every second in design mode
     */
    var currentIndex by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L) // 1 second
            currentIndex = (currentIndex + 1) % colorsOfProvider.size
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(products) { item ->

            ItemProductAllInRow(
                item,
                if (itemsToDesign == DesignS.PROVIDER_DESIGN) {
                    colorsOfProvider[currentIndex]
                } else colorsOfProvider[providerColorMap[item.provider] ?: 0],
                vmProduct
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        if (itemsToDesign == DesignS.PROVIDER_DESIGN) {
                            colorsOfProvider[currentIndex]
                        } else colorsOfProvider[providerColorMap[item.provider] ?: 0],
                    ),

                )
        }
    }
}

@Composable
fun AllInRowAtCell(
    products: List<Product>,
    vmProduct: ViewModelProduct,
) {
    val allSettings by vmProduct.allSettings.collectAsState()
    val itemsToDesign by vmProduct.itemToDesign.collectAsState()
    val colorsOfProvider = listOf(Color(allSettings.colorOfProvider), Color(allSettings.colorOfProviderSecond))
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

    /**
     * Switch color at provider every second in design mode
     */
    var currentIndex by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L) // 1 second
            currentIndex = (currentIndex + 1) % colorsOfProvider.size
        }
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
                        color = if(itemsToDesign == DesignS.PROVIDER_DESIGN ){
                            colorsOfProvider[currentIndex]
                        } else colorsOfProvider[providerColorMap[item.provider] ?: 0],
                        width = 2.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
            ) {
                ItemProductAllInRowAtCell(
                    item,
                    if (itemsToDesign == DesignS.PROVIDER_DESIGN) {
                        colorsOfProvider[currentIndex]
                    } else colorsOfProvider[providerColorMap[item.provider] ?: 0],
                    vmProduct = vmProduct
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProviderHeader(
    groupByProducts: Map<String, List<Product>>,
    vmProduct: ViewModelProduct,
) {

    val allSettings by vmProduct.allSettings.collectAsState()

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
                            .background(Color(allSettings.colorOfProviderBackground))
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        text = provider,
                        textAlign = TextAlign.Center,
                        color = Color(allSettings.colorOfProvider),
                        fontSize = allSettings.fontSizeOfProvider.sp,
                        fontFamily = Font.mapFontsFamily[allSettings.fontFamilyOfProvider],
                        fontWeight = if (allSettings.decorationOfProvider[0] == '1') FontWeight.Bold
                        else FontWeight.Normal,
                        fontStyle = if (allSettings.decorationOfProvider[1] == '1') FontStyle.Italic
                        else FontStyle.Normal,
                        textDecoration = if (allSettings.decorationOfProvider[2] == '1') TextDecoration.Underline
                        else TextDecoration.None,
                    )
                }
            }
            items(products) { product ->
                ItemProductProviderHeader(
                    product,
                    vmProduct
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

//fun highlightWordsInText(text: String, parties: List<String>): AnnotatedString {
//    val lowercaseParties = parties.map { it.lowercase() }
//
//    return buildAnnotatedString {
//        var currentIndex = 0
//
//        val regex = Regex("\\b\\w+\\b")
//        val matches = regex.findAll(text)
//
//        for (match in matches) {
//            val word = match.value
//            val start = match.range.first
//
//            // Add the text before the current word (if any)
//            if (currentIndex < start) {
//                append(text.substring(currentIndex, start))
//            }
//
//            if (lowercaseParties.contains(word.lowercase())) {
//                withStyle(style = SpanStyle(color = Color.Red)) {
//                    append(word)
//                }
//            } else {
//                append(word)
//            }
//
//            currentIndex = match.range.last + 1
//        }
//
//        // Append the rest of the string after the last match
//        if (currentIndex < text.length) {
//            append(text.substring(currentIndex))
//        }
//    }
//
//}