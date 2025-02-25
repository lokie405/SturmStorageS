package com.seryoga.sturmstorages.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class MyViewModel : ViewModel() {
//    first state whether the search is happening or not
    private val _isSearching = MutableStateFlow(false)
    val isSearching  = _isSearching.asStateFlow()

//    second state the text typed by the user
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

//    third state the list to be filtered
//    var productViewModel = ProductViewModel()
//    private val _providersList = MutableStateFlow()
}