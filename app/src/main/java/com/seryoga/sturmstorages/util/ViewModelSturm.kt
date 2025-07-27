package com.seryoga.sturmstorages.util

import SettingStoreManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.seryoga.sturmstorages.model.DesignS
import com.seryoga.sturmstorages.screen.SettingItem
import com.seryoga.sturmstorages.ui.theme.SturmStorageSTheme
import com.seryoga.sturmstorages.util.Const.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

class ViewModelSturm : ViewModel() {



    /* TOP */
    var isShowProviderList by mutableStateOf(false)
    var topElementHeight by mutableStateOf(56.dp)
    var topHeight by mutableStateOf(70.dp)
//    val topVerticalAlignment by mutableStateOf(Alignment.CenterVertically)
    private val _topVerticalAlignment = MutableStateFlow<Alignment.Vertical>(Alignment.CenterVertically)
    val topVerticalAlignment: StateFlow<Alignment.Vertical> = _topVerticalAlignment

    fun setTopVerticalAlignment(value: Alignment.Vertical) {
        _topVerticalAlignment.value = value

    }


    var textFieldProviderValue by mutableStateOf(
        TextFieldValue("")
    )

    fun setTextFieldProvider(provider: String) {
        textFieldProviderValue = TextFieldValue(
            text = provider,
            selection = TextRange(provider.length)
        )
    }


    /* CONTENT */
    var contentWeight by mutableStateOf(1f)




}
