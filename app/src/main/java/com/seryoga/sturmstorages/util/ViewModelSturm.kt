package com.seryoga.sturmstorages.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class ViewModelSturm : ViewModel() {

    var textProvider by mutableStateOf("")

    /* TOP */
    var isShowProviderList by mutableStateOf(false)
    var topElementHeight by mutableStateOf(56.dp)
    var topHeight by mutableStateOf(56.dp)
    var topVerticalAlignment by mutableStateOf(Alignment.CenterVertically)
    var topWeight by mutableStateOf(1f)
    var bottomHeight by mutableStateOf(56.dp)



    var providerFilter by mutableStateOf("")
        private set
    var textFieldProviderValue by mutableStateOf(
        TextFieldValue( "" )
    )

    fun setProvider(provider: String) {
        providerFilter = provider
        textFieldProviderValue = TextFieldValue(
            text = provider,
            selection = TextRange(provider.length)
        )
    }


    /* CONTENT */
    var contentWeight by mutableStateOf(1f)

}