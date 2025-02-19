package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seryoga.sturmstorages.db.ProductViewModel
import com.seryoga.sturmstorages.ui.theme.MainColor

@Composable
fun MainScreen(viewModel: ProductViewModel = viewModel()) {

//    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Column(
        modifier = Modifier
//            .fillMaxSize()
//            .consumeWindowInsets(WindowInsets.navigationBars)
            .background(MainColor)
            .systemBarsPadding()
            .imePadding()
            .navigationBarsPadding()
    ) {

        Box(modifier = Modifier.height(56.dp)) { TopBar(viewModel) }
        Box(modifier = Modifier.weight(1f)) { Content(viewModel) }
        Box(modifier = Modifier.height(64.dp)) { BottomBar(viewModel) }
    }
}

