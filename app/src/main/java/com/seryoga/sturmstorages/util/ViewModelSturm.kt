package com.seryoga.sturmstorages.util

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.seryoga.sturmstorages.util.Const.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

class ViewModelSturm : ViewModel() {

//    private val _screen = MutableStateFlow<Screen>(Screen.SETTING_SCREEN)
//    val screen: StateFlow<Screen> = _screen
//    fun setScreen(newScreen: Screen) {
//        Log.i(TAG, ")))))))viewModel${screen.value}");
//        _screen.value = newScreen
//    }
//    var showingScreen : Flow<Int> = flowOf(Const.MAIN_SCREEN)
//    var sh = mutableIntStateOf(Const.MAIN_SCREEN)
//    var textProvider by mutableStateOf("")



    /* TOP */
    var isShowProviderList by mutableStateOf(false)
    var topElementHeight by mutableStateOf(56.dp)
    var topHeight by mutableStateOf(70.dp)
    var topVerticalAlignment by mutableStateOf(Alignment.CenterVertically)
//    var topWeight by mutableStateOf(1f)
//    var bottomHeight by mutableStateOf(56.dp)


//
//    var pf by mutableStateOf("")
//        private set
//
//    var providersList by mutableStateOf(emptyList<String>())
//        private set
//    fun setProviderList(list: List<String>){
////        TODO() Log here for understend why providersList not updated right
//        Log.i(TAG, "--ViewModelSturm: LIST: ${list}")
//        providersList = list
//    }


    //    var isChoseProvider = false
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

//fun<T> switch(condition: Boolean, case1: T ,case2: T): T{
//    return if(condition) case1 else case2
//}