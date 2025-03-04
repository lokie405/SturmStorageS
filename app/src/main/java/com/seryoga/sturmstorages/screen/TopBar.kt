package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ModifiedVM
import com.seryoga.sturmstorages.util.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: ProductViewModel, modifiedVM: ModifiedVM) {

    var expanded by remember { mutableStateOf(false) }
    var chosenProvider by remember { mutableStateOf("") }
    val listOfProviders by viewModel.providers.observeAsState(initial = emptyList())

//    val searchProvider by viewModel.searchText.collectAsState()
//    val isSearching by viewModel.isSearching.collectAsState()
//    val providerList by viewModel.providersList.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = modifiedVM.topVerticalyAlignment.value
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .height(modifiedVM.topElementHeight.value)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "22/12",
                fontSize = 16.sp,
                fontFamily = Font.jetBrainMonoBold,
                color = ColorGreen
            )
        }
        Box(
            modifier = Modifier
                .height(modifiedVM.topElementHeight.value)
            ,
            contentAlignment = Alignment.Center
        ) {

            IconButton(
                onClick = { expanded = true },
            ) {
                if (chosenProvider.isEmpty()) {

                    Icon(
                        painter = painterResource(R.drawable.clear_icon),
                        tint = Color.White,
                        contentDescription = "All providers"
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.clear_icon),
                        tint = Color.White,
                        contentDescription = "All providers"
                    )

                }
//            Text(chosenProvider)
            }
        }
        SearchProvider(modifiedVM)

        Box(
            modifier = Modifier
                .height(modifiedVM.topElementHeight.value),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Select All",
                modifier = Modifier
                    .clickable {
                        chosenProvider = ""
                        viewModel.providerFilter("%")

                    },
            )
        }


    }
}

@Composable
fun SearchProvider(modifiedVM: ModifiedVM) {
    var searchProvider by remember { mutableStateOf("") }
    var isClicked by remember { mutableStateOf(false) }


    Text(
        text = "Some provider text",
        modifier = Modifier
            .clickable{
                isClicked = !isClicked
                if (isClicked) {
//                    modifiedVM.topHeight.value = 300.dp
                    modifiedVM.topVerticalyAlignment.value = Alignment.Top
                    modifiedVM.isShowProviderList.value = true
//                    modifiedVM.topWeight.value = 1f
//                    modifiedVM.contentWeight.value = 0f
                }
                else {
//                    modifiedVM.topHeight.value = 56.dp
                    modifiedVM.isShowProviderList.value = false
                    modifiedVM.topVerticalyAlignment.value = Alignment.CenterVertically
//                    modifiedVM.topWeight.value = 0f
//                    modifiedVM.contentWeight.value = 1f
                }
            }
    )
//    TextField(
//        value = searchProvider,
//        onValueChange = { searchProvider = it },
//        label = { Text("Search") },
//        modifier = Modifier
//            .clickable {
//                Log.i(TAG, "--TopBar: CLICK")
//                isClicked = !isClicked
//                measureVM.topHeight.value = 300.dp
//            },
//        singleLine = true,
//
//        )
}