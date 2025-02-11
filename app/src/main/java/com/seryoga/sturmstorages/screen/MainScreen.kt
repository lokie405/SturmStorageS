package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.db.ProductViewModel
import com.seryoga.sturmstorages.ui.theme.ColorBlue
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.ColorLightGrey
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.MainColor

@Composable
fun MainScreen(viewModel: ProductViewModel = viewModel()) {

//  val products by viewModel.productList("All").observeAsState(listOf())
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Column(
        modifier = Modifier
            .background(MainColor)
    ) {
        Box(modifier = Modifier.weight(0.1f)) { TopBar(viewModel, statusBarHeight) }
        Box(modifier = Modifier.weight(0.8f)) { Content(viewModel) }
        Box(modifier = Modifier.weight(0.1f)) { BottomBar() }
    }
}

@Composable
fun TopBar(viewModel: ProductViewModel, padding: Dp) {

    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf("Select Item") }
    val listProvider = listOf(
        "*",
        "УЗП - Електроiнструмент",
        "УЗП-ЕДон",
        "Технозбут",
        "ЧАС",
        "Хозтов",
        "УЗП - Ручний iнструмент",
        "Булавський/Наумчук"
    )

    Box(
        modifier = Modifier
            .padding(top = padding)
            .background(MainColor)
    ) {
        Row() {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .fillMaxHeight(),
//          .weight(0.2f)
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "22.12",
                    fontSize = 16.sp,
                    fontFamily = Font.jetBrainMonoBold,
                    color = ColorGreen

                )
            }
            Box(
                modifier = Modifier,
            )
            Button(onClick = { expanded = true }) {
                Text(selectedOption)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listProvider.forEach { chosenProvider ->
                    DropdownMenuItem(
                        text = { Text(chosenProvider) },
                        onClick = {
                            selectedOption = chosenProvider
                            expanded = false
                            viewModel.setFilter(chosenProvider)
//              Log.i("MyLog", "--MainScreen: ${viewModel.productList(chosenProvider).value?.size}")
//              Content(viewModel.getProductsAsProvider(selectedOption))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Content(viewModel: ProductViewModel) {
    val filter by viewModel.filter.collectAsState()
    val products by viewModel.products.collectAsState()

    viewModel.setFilter("Арсенал")
//  val listOfProducts = remember { derivedStateOf { products } }
//  Log.i("MyLog", "--MainScreen: PRODUCT: ${listOfProducts.value.size}")

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally
//      .align(Alignment.Center)
//      .padding(start = 4.dp)
    ) {
//    itemsIndexed(listOfProducts.value) { index, item ->
//    item{Text(text = "main")}
        items(products) { item ->

            ItemProduct(item)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 4.dp)
                    .background(ColorBlue),

                )
        }
    }
}

@Composable
fun BottomBar() {

}

@Composable
fun ItemProduct(item: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(MainColor)
    ) {
        Box(
            modifier = Modifier
                .weight(0.5f)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = item.name,
                color = ColorLightGrey,
                fontSize = 13.sp,
                fontFamily = Font.jetBrainMonoBold

            )
        }
        Box(
            modifier = Modifier
                .weight(0.2f)
//          .background(Color.Gray)
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
                        text = item.quantity,
                        fontSize = 12.sp,
                        color = ColorGreen,
                        fontFamily = Font.jetBrainMonoBold
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                ) {
                    Text(
                        text = item.price,
                        fontSize = 11.sp,
                        color = Color.Yellow,
                        fontFamily = Font.jetBrainMonoMedium
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(0.3f)
                .align(Alignment.CenterVertically)
                .padding(horizontal = 4.dp),
//contentAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.provider,
                fontSize = 11.sp,
                color = ColorMagenta,
                fontFamily = Font.jetBrainMonoMedium
            )
        }
    }
}
