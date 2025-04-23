package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.ui.theme.Font

@Composable
fun DesignTitle(title: String) {
//        Spacer(Modifier.height(20.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        Text(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            text = title,
            fontFamily = Font.jetBrainMonoBold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
        Spacer(Modifier.height(2.dp))
}