package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seryoga.sturmstorages.util.ProductViewModel
import com.seryoga.sturmstorages.ui.theme.MainColor
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun MainScreen(viewModel: ProductViewModel = viewModel(), vmSturm: ViewModelSturm = viewModel()) {
    var topHeight = remember { mutableStateOf(Const.TOP_BAR_HEIGHT) }

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

        Box(
            modifier = Modifier
                .then(
                    if (vmSturm.isShowProviderList) Modifier.weight(1f)
                    else Modifier.height(vmSturm.topHeight)
                )
        ) { TopBar(viewModel, vmSturm) }
        Box(modifier = Modifier.then(
            if(vmSturm.isShowProviderList) Modifier.height(0.dp)
            else Modifier.weight(vmSturm.contentWeight))
        )
        { Content(viewModel) }
        Box(modifier = Modifier.height(Const.BOTTOM_BAR_HEIGHT)) { BottomBar(viewModel) }
    }
}

