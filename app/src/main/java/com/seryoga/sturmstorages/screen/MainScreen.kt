package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seryoga.sturmstorages.R
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

//    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Column(
        modifier = Modifier
            .background(MainColor)
//            .consumeWindowInsets(WindowInsets.navigationBars)
            .systemBarsPadding()
            .imePadding()
            .navigationBarsPadding()
    ) {

        Box(modifier = Modifier.height(56.dp)) { TopBar(viewModel) }
        Box(modifier = Modifier.weight(1f)) { Content(viewModel) }
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .height(56.dp)
        ) { BottomBar(viewModel) }
    }
}

//@Composable
//fun TopBar(viewModel: ProductViewModel) {
//
//
//}

//@Composable
//fun Content(viewModel: ProductViewModel) {
//
//}

//@Composable
//fun BottomBar(viewModel: ProductViewModel) {
//
//
//}

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
