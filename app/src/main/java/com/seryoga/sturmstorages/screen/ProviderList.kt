package com.seryoga.sturmstorages.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.Const.TAG
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun ProviderList(
//    modifier: Modifier = Modifier,
    vmSturm: ViewModelSturm,
    vmProduct: ViewModelProduct,
    listOfProvider: List<String>,
//    searchedProviderText: String,
    searchedProviderCallback: (str: String) -> Unit,
//    modifier: Modifier = Modifier,
) {
//    Log.i(TAG, "--TestList: Start ${vmSturm.isShowProviderList}")
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var isFocused by remember { mutableStateOf(true) }
    var isSelect by remember { mutableStateOf(false) }
//    var text by remember { mutableStateOf("FOCUS") }
//    var providerList by remember { mutableStateOf(listOfProvider) }

//    focusManager.clearFocus()
    Column(modifier = Modifier) {
        Box(
            modifier = Modifier
//                .fillMaxWidth()
                .onFocusChanged { isFocused = !isFocused }
        )

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
                            focusManager.clearFocus()
//                        vmSturm.setProvider("")
                            searchedProviderCallback("")
                            vmSturm.textFieldProviderValue =
                                vmSturm.textFieldProviderValue.copy(text = "")
                            vmProduct.providerInput = "%"
//                            vmProduct.providerFilter("%")
//                            vmProduct.productsInput = listOf("%")
//                                providersFiltered = providers
                        })
                )
            },
            value = vmSturm.textFieldProviderValue,
            onValueChange = { newText ->
                isFocused = true
//                if (newText.text.isEmpty()) { }
                vmSturm.textFieldProviderValue = newText
                searchedProviderCallback(newText.text)
            },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                isFocused = true
                                isSelect = false
//                                Log.i(TAG, "--TestList: isShowProviderList: ${vmSturm.isShowProviderList}")
//                                Log.i(TAG, "--TestList: isFocused: ${isFocused}")
//                                Log.i(TAG, "--TestList: isSelect: ${isSelect}")
                            }
                        }
                    }
                }
        )


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

            items(listOfProvider) { provider ->
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clickable(onClick = {
                            vmSturm.textFieldProviderValue = TextFieldValue(
                                text = provider,
                                selection = TextRange(provider.length)
                            )
                            vmProduct.providerInput = provider
                            vmProduct.loadProducts()
                            isSelect = true
                            searchedProviderCallback(provider)
                            keyboardController?.hide()
                        }),
                    text = provider,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold

                )
            }
        }

    }
}

//@Composable
//fun NewListOfProvider(
//    vmSturm: ViewModelSturm,
//    vmProduct: ViewModelProduct,
//    listOfProvider: List<String>,
////    searchedProviderText: String,
//    searchedProviderCallback: (str: String) -> Unit,
//) {
//
//    var focusManager = LocalFocusManager.current
//    val keyboardController = LocalSoftwareKeyboardController.current
//    var isFocused by remember { mutableStateOf(false) }
//    var isSelect by remember { mutableStateOf(false) }
//    var text by remember { mutableStateOf("FOCUS") }
//    var providerList by remember { mutableStateOf(listOfProvider) }
//

//    BasicTextField(
//        value = vmSturm.textFieldProviderValue,
//        onValueChange = { newText ->
//            isFocused = true
//            if (newText.text.isEmpty()) {
//                Log.i(TAG, "--TopBar: Null value ")
//            }
//            vmSturm.textFieldProviderValue = newText
////                Log.i(TAG, "--TestList: Change ${newText.text}")
////                vmSturm.setProviderList(
////                    vmProduct.providers.filter { it.contains(newText.text, ignoreCase = true) }
////                )
////                text = newText
//            searchedProviderCallback(newText.text)
//        },
//        modifier = Modifier.height(36.dp),
//        singleLine = true,
//
//    ){
//        innerTextField ->
//        TextFieldDefaults.OutlinedTextFieldDecorationBox(
//
//        )
//    }

//}