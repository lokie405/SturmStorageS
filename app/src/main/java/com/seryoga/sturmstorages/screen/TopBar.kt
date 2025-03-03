package com.seryoga.sturmstorages.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.Const.TAG
import com.seryoga.sturmstorages.util.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: ProductViewModel) {

    var expanded by remember { mutableStateOf(false) }
    var chosenProvider by remember { mutableStateOf("") }
    val listOfProviders by viewModel.providers.observeAsState(initial = emptyList())

//    val searchProvider by viewModel.searchText.collectAsState()
//    val isSearching by viewModel.isSearching.collectAsState()
//    val providerList by viewModel.providersList.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f),
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
            modifier = Modifier,
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
//            SearchBarSample()
//        DockedSearchBarSample(listOfProviders)
        Test2(listOfProviders)
        Box() {
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
fun Test2(listOfProviders: List<String>) {
    var searchText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
//    val searchResults = listOf("Item A", "Item B", "Item C")

    Log.i(TAG, "--TopBar: List: ${listOfProviders}")
//    val filteringList = remember { mutableListOf(*listOfProviders.toTypedArray()) }
    val filteringList = listOfProviders.toMutableList()
    Log.i(TAG, "--TopBar: ListState: ${filteringList}")
    var isFocused by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                isExpanded = searchText.isNotEmpty()
            },

            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                    isExpanded = it.isFocused
                },
            placeholder = { Text("Search...") },
            interactionSource = interactionSource
        )
        if (isFocused) Log.i(TAG, "--TopBar: IS FOCUSED")
        else Log.i(TAG, "--TopBar: NOT FOCUSED")

        if (isExpanded) {
            val density = LocalDensity.current
            Popup(
                offset = with(density) {
                    IntOffset(
                        0.dp.toPx().toInt(),
                        Const.TOP_BAR_HEIGHT.toPx().toInt()
                    )
                }, // Convert dp to pixels
                alignment = Alignment.TopStart, // Align with screen, not parent
                onDismissRequest = { isExpanded = false }
            ) {
                Box(
                    modifier = Modifier
//                        .fillMaxWidth()
                        .background(Color.White)
                        .border(1.dp, Color.Gray)
                        .padding(8.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        for (i in 0..< filteringList.size) {
                            Log.i(TAG, "--TopBar: i = $i; ${filteringList[i].toString()}")
//                        searchResults.forEach { result ->
                            Text(
                                text = filteringList[i].toString(),
                                modifier = Modifier
//                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        searchText = filteringList[i].toString()
                                        isExpanded = false
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun Test() {
//    var searchText by remember { mutableStateOf("") }
//    var isExpanded by remember { mutableStateOf(false) }
//    val searchResults = listOf("Item 1", "Item 2", "Item 3") // Example list
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp)
//            .background(Color.White),
//        contentAlignment = Alignment.Center
//    ) {
//        Column {
//            TextField(
//                value = searchText,
//                onValueChange = {
//                    searchText = it
//                    isExpanded = searchText.isNotEmpty() // Show dropdown only if text is entered
//                },
//                modifier = Modifier.fillMaxWidth(),
//                placeholder = { Text("Search...") }
//            )
//
//            DropdownMenu(
//                expanded = isExpanded,
//                onDismissRequest = { isExpanded = false },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                searchResults.forEach { result ->
//                    DropdownMenuItem(
//                        text = { Text(result) },
//                        onClick = {
//                            searchText = result
//                            isExpanded = false
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DockedSearchBarSample(listOfProviders: List<String>) {
//    val textFieldState = rememberTextFieldState()
//    var expanded by rememberSaveable { mutableStateOf(false) }
//
//    Box(Modifier
//        .fillMaxSize()
//        .semantics { isTraversalGroup = true }) {
//        DockedSearchBar(
//            modifier =
//            Modifier
//                .align(Alignment.TopCenter)
//                .padding(top = 8.dp)
//                .semantics {
//                    traversalIndex = 0f
//                },
//            inputField = {
//                SearchBarDefaults.InputField(
//                    state = textFieldState,
//                    onSearch = { expanded = false },
//                    expanded = expanded,
//                    onExpandedChange = { expanded = it },
//                    placeholder = { Text("Hinted search text") },
//                    leadingIcon = {
//                        Icon(
//                            painterResource(R.drawable.reset_icon),
//                            contentDescription = null
//                        )
//                    },
//                    trailingIcon = {
//                        Icon(
//                            painterResource(R.drawable.reset_icon),
//                            contentDescription = null
//                        )
//                    },
//                )
//            },
//            expanded = expanded,
//            onExpandedChange = { expanded = it },
//        ) {
//            Column(Modifier.verticalScroll(rememberScrollState())) {
//                repeat(4) { idx ->
//                    val resultText = "Suggestion $idx"
//                    ListItem(
//                        headlineContent = { Text(resultText) },
//                        supportingContent = { Text("Additional info") },
//                        leadingContent = {
//                            Icon(
//                                painterResource(R.drawable.reset_icon),
//                                contentDescription = null
//                            )
//                        },
//                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
//                        modifier =
//                        Modifier
//                            .clickable {
//                                textFieldState.setTextAndPlaceCursorAtEnd(resultText)
//                                expanded = false
//                            }
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 4.dp)
//                    )
//                }
//            }
//        }
//
//    }
//}