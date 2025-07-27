package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.seryoga.sturmstorages.R

@Composable
fun ScreenTitleMain(
    title: String = "",
    content: (@Composable BoxScope.() -> Unit)? = null,
    onClickBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .systemBarsPadding()
//            .height(Const.TOP_BAR_HEIGHT)
            .background(MaterialTheme.colorScheme.background),

//            .padding(start = 10.dp, top = Const.TOP_BAR_HEIGHT ),
    ) {
        IconButton(
            modifier = Modifier
//                .align(Alignment.TopStart),
                ,
            onClick = {
                onClickBack()
            }
        ) {
            Icon(
                modifier = Modifier
//                .padding(bottom = 30.dp)
                ,
                painter = painterResource(R.drawable.back_icon),
                contentDescription = stringResource(R.string.back_button),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        if (content == null) {
            ScreenTitleText(title)
        } else {
            content()
        }
    }
}