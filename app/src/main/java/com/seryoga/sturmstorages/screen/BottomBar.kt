package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.DarkGrey
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun BottomBar(vmProduct: ViewModelProduct, vmSturm: ViewModelSturm, clearAllCallback: () -> Unit) {
    var product by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
//    val keyboardController = LocalSoftwareKeyboardController.current

    //NOTE: Show keyboard or not
//    LaunchedEffect(Unit) {
//        focusRequester.requestFocus() // Request focus when the composable is launched
//    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(DarkGrey),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = ColorMagenta,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
//                    .focusable()
                    .focusRequester(focusRequester)
                ,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                shape = RoundedCornerShape(8.dp),
                value = product,
                onValueChange = {
                    product = it

                    vmProduct.productFilter(product)
                },
                textStyle = TextStyle(fontFamily = Font.jetBrainMonoMedium),
                label = {
                    Text(
                        text = stringResource(R.string.name_of_product),
                        fontFamily = Font.jetBrainMonoBold
                    )
                },
                leadingIcon = {
                    IconButton(onClick = {
                        product = ""
                        vmProduct.productFilter("")
                    }) {
                        if (product.isEmpty()) {

                            Icon(
                                modifier = Modifier
                                    .padding(start = 6.dp),
                                tint = ColorMagenta,
                                painter = painterResource(R.drawable.search_icon),
                                contentDescription = "Clear product",
                            )
                        } else {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 6.dp),
                                tint = ColorMagenta,
                                painter = painterResource(R.drawable.clear_icon),
                                contentDescription = "Clear product",
                            )
                        }
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            product = ""
                            vmProduct.productFilter("")
                            vmProduct.providerFilter("%")
                            vmSturm.setTextFieldProvider("")
                            vmSturm.isShowProviderList = false
                            clearAllCallback()
                        }
                    ) {

                        Icon(
                            modifier = Modifier
                                .padding(start = 6.dp),
                            tint = ColorMagenta,
                            painter = painterResource(R.drawable.reset_icon),
                            contentDescription = "Clear product",
                        )
                    }
                }
            )
        }
    }
}
