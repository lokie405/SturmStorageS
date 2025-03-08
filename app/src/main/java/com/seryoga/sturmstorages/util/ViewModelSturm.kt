package com.seryoga.sturmstorages.util

import androidx.compose.material3.TextField
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
    var textFieldValue by mutableStateOf(
        TextFieldValue( providerFilter )
    )

    fun setProvider(provider: String) {
        providerFilter = provider
        textFieldValue = TextFieldValue(
            text = provider,
            selection = TextRange(provider.length)
        )
    }

//    fun providerFilter(provider: String) {
//        providerFilter = provider
//    }

    /* CONTENT */
    var contentWeight by mutableStateOf(1f)

}