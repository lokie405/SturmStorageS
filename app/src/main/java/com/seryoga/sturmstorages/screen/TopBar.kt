@file:Suppress("UNREACHABLE_CODE")

package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ProductViewModel
import com.seryoga.sturmstorages.util.ViewModelSturm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: ProductViewModel, vmSturm: ViewModelSturm) {

    var expanded by remember { mutableStateOf(false) }
    var chosenProvider by remember { mutableStateOf("") }
//    val listOfProviders by viewModel.providers.observeAsState(initial = emptyList())


//    val searchProvider by viewModel.searchText.collectAsState()
//    val isSearching by viewModel.isSearching.collectAsState()
//    val providerList by viewModel.providersList.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = vmSturm.topVerticalAlignment
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .height(vmSturm.topElementHeight),
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
                .height(vmSturm.topElementHeight),
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
        FocusTrackingTextField(vmSturm, viewModel)
    }
}


@Composable
fun FocusTrackingTextField(vmSturm: ViewModelSturm, viewModel: ProductViewModel) {
    val providers = viewModel.providers
    var isSelect by remember { mutableStateOf(false) }
    var providersFiltered by remember { mutableStateOf(providers)}
    var isFocused by remember { mutableStateOf(false) }


    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused },
        )
        {
            OutlinedTextField(
                modifier = Modifier,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = ColorMagenta,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                ),
                label = {
                    Text(
                        text = "Provider",
                        fontFamily = Font.jetBrainMonoBold
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.clear_icon),
                        contentDescription = "Clear Provider",
                        modifier = Modifier
                            .clickable(onClick = {
                                isFocused = false
                                vmSturm.setProvider("")
                                viewModel.providerFilter("%")
//                                providersFiltered = providers
                            })
                    )
                },
                value = vmSturm.textFieldProviderValue,
                onValueChange = { newValue ->
                    isFocused = true
                    if (newValue.text.isEmpty()) providersFiltered = providers
                    vmSturm.textFieldProviderValue = newValue
                    providersFiltered =
                        providers.filter { it.contains(newValue.text, ignoreCase = true) }
                    if (providersFiltered.size == 1) {
                        isSelect = true
                    } else {
                        isSelect = false
                    }
                },
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    isFocused = true
                                }
                            }
                        }
                    }
            )
        }

        if (isFocused && !isSelect) {  // Toggle show/hide list of provider
            vmSturm.isShowProviderList = true
        } else {
            vmSturm.isShowProviderList = false
        }
        LazyColumn(
            modifier = Modifier
                .then(
                    if (vmSturm.isShowProviderList) {
                        Modifier.fillMaxHeight()
                    } else {
                        Modifier.height(0.dp)
                    }
                )
                .padding(8.dp)
        ) {
            items(providersFiltered) { provider ->
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clickable(onClick = {
                            vmSturm.textFieldProviderValue = TextFieldValue(
                                text = provider,
                                selection = TextRange(provider.length)
                            )
                            viewModel.providerFilter(provider)
                            isSelect = true
                        }),
                    text = provider,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }
}