package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.ui.theme.ColorYellow
import com.seryoga.sturmstorages.ui.theme.Font

@Composable
fun ScreenTitleTextClicked(
    list: List<Int>,
    isSelected: Boolean,
    onClick: () -> Unit,
    fontSize: Int = 28,
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ){
        Icon(
            modifier = Modifier
                .padding(start = 10.dp, end = 20.dp),
            painter = painterResource(list[1]),
            tint = if(isSelected) ColorYellow else MaterialTheme.colorScheme.onPrimary,
            contentDescription = stringResource(list[0])
        )
        Column() {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(list[0]),
            fontSize = fontSize.sp,
            fontFamily = Font.jetBrainMonoBold,
            textAlign = TextAlign.Center,
            color = if(isSelected) ColorYellow else MaterialTheme.colorScheme.onPrimary,

        )
//            Spacer(
//                modifier = Modifier
//                .padding(3.dp)
//                    .background(if(isSelected) ColorYellow else Color.Transparent),
//
//            )
        }
    }
}

