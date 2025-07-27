package com.seryoga.sturmstorages.screen

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun ProviderList(
    vmSturm: ViewModelSturm,
    vmProduct: ViewModelProduct,
    listOfProvider: List<String>,
    searchedProviderCallback: (str: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var isFocused by remember { mutableStateOf(true) }
    var isSelect by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }
    var columnHeight by remember {
        mutableStateOf(
            if (vmSturm.isShowProviderList) Modifier.fillMaxHeight()
            else Modifier.height(0.dp)
        )
    }
    var fontSize by remember { mutableStateOf(16.sp) }

    key(isVisible) {
        if (isVisible) vmSturm.setTopVerticalAlignment(Alignment.Top) else vmSturm.setTopVerticalAlignment(Alignment.CenterVertically)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
    )
    {
        Box(
            modifier = Modifier
                .onFocusChanged { isFocused = !isFocused }
        )
        OutlinedTextField(
            modifier = Modifier,
            textStyle = TextStyle(fontSize = fontSize),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = ColorMagenta,
                unfocusedBorderColor = ColorMagenta,
                focusedBorderColor = ColorMagenta,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            ),
            label = {
                Text(
                    text = "Provider",
                    fontFamily = Font.jetBrainMonoBold,
//                    fontSize = 18.sp
                )
            },
            trailingIcon = {
                if (isVisible) {
                    Icon(
                        painter = painterResource(R.drawable.clear_icon),
                        contentDescription = "Clear Provider",
                        modifier = Modifier
                            .clickable(onClick = {
                                isFocused = false
                                isVisible = false
                                focusManager.clearFocus()
                                searchedProviderCallback("")
                                vmSturm.textFieldProviderValue =
                                    vmSturm.textFieldProviderValue.copy(text = "")
                                vmProduct.providerInput = "%"
                            })
                    )
                }
            },
            value = vmSturm.textFieldProviderValue,

            onValueChange = { newText ->

                isFocused = true
                vmSturm.textFieldProviderValue = newText
                searchedProviderCallback(newText.text)
                isVisible = if (newText.text.isNotEmpty()) true else false
                fontSize = if (newText.text.length <= 19) 16.sp
                else if (newText.text.length <= 25) 13.sp
                else 11.sp
                Log.i("MyLog", "NEW VALUE : ${newText.text}");
            },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                isFocused = true
                                isSelect = false
                            }
                        }
                    }
                }
        )


        if (isFocused && !isSelect) {  // Toggle show/hide list of provider
            vmSturm.isShowProviderList = true
            columnHeight = Modifier.fillMaxHeight()
        } else {
            vmSturm.isShowProviderList = false
            columnHeight = Modifier.height(0.dp)
        }
        LazyColumn(
            modifier = Modifier
                .then(columnHeight)
                .padding(8.dp)
        ) {

            items(listOfProvider) { provider ->
                Text(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .clickable(onClick = {
                            fontSize = if (provider.length <= 19) 16.sp
                            else if (provider.length <= 25) 13.sp
                            else 11.sp
                            isVisible =
                                if (provider != "Provider" || provider.isNotEmpty()) true else false
                            vmSturm.textFieldProviderValue = TextFieldValue(
                                text = provider,
                                selection = TextRange(provider.length)
                            )
                            vmProduct.providerInput = provider
                            vmProduct.displayProducts()
                            isSelect = true
                            searchedProviderCallback(provider)
                            keyboardController?.hide()
                        }),
                    fontSize = 16.sp,
                    text = provider,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold

                )
            }
        }

        BackHandler(enabled = isFocused) {
            isFocused = false
            focusManager.clearFocus()
        }
    }
}
