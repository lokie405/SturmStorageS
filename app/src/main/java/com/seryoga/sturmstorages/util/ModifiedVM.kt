package com.seryoga.sturmstorages.util

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class ModifiedVM : ViewModel() {
    var isShowProviderList = mutableStateOf(false)
    var topElementHeight = mutableStateOf(56.dp)
    var topHeight = mutableStateOf(56.dp)
    var topVerticalyAlignment = mutableStateOf(Alignment.CenterVertically)
    var topWeight = mutableStateOf(1f)
    var contentWeight = mutableStateOf(1f)
    var bottomHeight = mutableStateOf(56.dp)

}