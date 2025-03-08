@file:Suppress("UNREACHABLE_CODE")

package com.seryoga.sturmstorages.screen

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const.TAG
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
//        Box(
//            modifier = Modifier
//                .height(vmSturm.topElementHeight),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                contentDescription = "Select All",
//                modifier = Modifier
//                    .clickable {
//                        chosenProvider = ""
//                        viewModel.providerFilter("%")
//
//                    },
//            )
//        }


    }
}


@Composable
fun FocusTrackingTextField(vmSturm: ViewModelSturm, viewModel: ProductViewModel) {
//    var text by remember { mutableStateOf("") }
//var text by remember {mutableStateOf("")}
//    val focusManager = LocalFocusManager.current
    val providers = viewModel.providers
//    var li = ""
//    var ss = remember{mutableStateOf(li)}
    var isSelect by remember { mutableStateOf(false) }
//    var providersFiltered by remember { mutableStateOf<List<String>>(providers) }
    var providersFiltered by remember { mutableStateOf(providers) }
//    Log.i(TAG, "--TopBar: PRRRRR: $providers")
//    val focusRequester = remember {FocusRequester()}
    var isFocused by remember { mutableStateOf(false) }

//    LaunchedEffect (vmSturm.textProvider){
//
//    }

//    val interactionSource = remember { MutableInteractionSource() }
    Column() {
        Box(
            modifier = Modifier
//                .focusRequester(focusRequester)
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                    Log.d("TextField", if (isFocused) "Поле у фокусі" else "Фокус втрачено")
                },
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
                                providersFiltered = providers
                            })
                    )
                },
                value = vmSturm.textFieldValue,
//                value = text,
                onValueChange = { newValue ->
                    isFocused = true
//                    newValue = text
//                    text = newValue
                    if (newValue.text.isEmpty()) providersFiltered = providers
                    vmSturm.textFieldValue = newValue
                    providersFiltered =
                        providers.filter { it.contains(newValue.text, ignoreCase = true) }
                    Log.i(TAG, "--TopBar: CHANGE ${providersFiltered.size}")
                    if (providersFiltered.size == 1) {
                        isSelect = true
                    } else {
                        isSelect = false
                    }


//                    Log.i(TAG, "--TopBar: onValueChange: ${ss.value}")
//                    vmSturm.providerFilter(vmSturm.textFieldValue.text)
                    Log.i(TAG, "--TopBar: kkkkk:${providersFiltered}")
                },
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
//                                    Log.i(TAG, "--TopBar: Click")
                                    isFocused = true
                                    // works like onClick
                                }
                            }
                        }
                    }
            )
        }

        if (isFocused && !isSelect) {
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
                        .padding(8.dp)
                        .clickable(onClick = {
//                            text = provider
                            vmSturm.textFieldValue = TextFieldValue(
                                text = provider,
                                selection = TextRange(provider.length)
                            )
                            viewModel.providerFilter(provider)
                            isSelect = true
//                            vmSturm.setProvider(provider)
//                            isFocused = false
                        }),
                    text = provider
                )
            }
        }
    }
//
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .onFocusChanged {
//            isFocused = it.isFocused
//            Log.d("TextField", if (isFocused) "Поле у фокусі" else "Фокус втрачено")
//        }) {
//        TextField(
//            value = text,
//            onValueChange = { text = it },
//            label = { Text("Введіть текст") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .focusRequester(focusRequester),
//            keyboardActions = KeyboardActions(
//                onDone = { focusManager.clearFocus() }
//            ),
//            keyboardOptions = KeyboardOptions.Default.copy(
//                imeAction = ImeAction.Done
//            )
//        )
//    }
}