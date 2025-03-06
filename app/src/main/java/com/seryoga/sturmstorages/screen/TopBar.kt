package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
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
            }
        }
        SearchProvider(modifiedVM, viewModel)

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchProvider(modifiedVM: ModifiedVM, viewModel: ProductViewModel) {
    val textFieldState = rememberTextFieldState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    var listProvider = remember { mutableStateListOf(viewModel.providers.value) }

    if (expanded) {
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
    Box(Modifier.fillMaxSize().semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter).semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    state = textFieldState,
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Hinted search text") },
                    leadingIcon = { Icon(painter = painterResource(R.drawable.search_icon), contentDescription = null) },
                    trailingIcon = { Icon(painter = painterResource(R.drawable.reset_icon), contentDescription = null) },
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                repeat(4) { idx ->
                    val resultText = "Suggestion $idx"
                    ListItem(
                        headlineContent = { Text(resultText) },
                        supportingContent = { Text("Additional info") },
                        leadingContent = { Icon(painter = painterResource(R.drawable.clear_icon), contentDescription = null) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier =
                        Modifier.clickable {
                            textFieldState.setTextAndPlaceCursorAtEnd(resultText)
                            expanded = false
                        }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }

//        LazyColumn(
//            contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.semantics { traversalIndex = 1f },
//        ) {
//            val list = List(100) { "Text $it" }
//            items(count = list.size) {
//                Text(
//                    text = list[it],
//                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
//                )
//            }
//        }
    }


//    Text(
//        text = "Some provider text",
//        modifier = Modifier
//            .clickable{
//                isClicked = !isClicked
//                if (isClicked) {
////                    modifiedVM.topHeight.value = 300.dp
//                    modifiedVM.topVerticalyAlignment.value = Alignment.Top
//                    modifiedVM.isShowProviderList.value = true
////                    modifiedVM.topWeight.value = 1f
////                    modifiedVM.contentWeight.value = 0f
//                }
//                else {
////                    modifiedVM.topHeight.value = 56.dp
//                    modifiedVM.isShowProviderList.value = false
//                    modifiedVM.topVerticalyAlignment.value = Alignment.CenterVertically
////                    modifiedVM.topWeight.value = 0f
////                    modifiedVM.contentWeight.value = 1f
//                }
//            }
//    )
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