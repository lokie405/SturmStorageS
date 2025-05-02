package com.seryoga.sturmstorages.screen.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.ButtonType
import com.seryoga.sturmstorages.screen.ButtonWithIcon
import com.seryoga.sturmstorages.screen.SpacerS
import com.seryoga.sturmstorages.ui.theme.Font


@Composable
fun DialogToChoosen(
//    type: DialogType = DialogType.NO_DIALOG,
    title: String,
    onDismissClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {},
    content: @Composable () -> Unit = {},

//dialogType:
) {
    Dialog(
        onDismissRequest = { onDismissClick() }) {
        Card(
//            modifier = Modifier

        ) {
            Column(
                modifier = Modifier
                .padding(10.dp)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontFamily = Font.jetBrainMonoBold,
                    lineHeight = 1.5.em,
                    text = title
                )
                SpacerS(10)
                content()
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ButtonWithIcon(
                        ButtonType.MEDIUM,
                        R.drawable.x_icon,
                        onClick = { onDismissClick() }
                    )
                    ButtonWithIcon(
                        ButtonType.MEDIUM,
                        R.drawable.ok_icon,
                        onClick = {
                            onConfirmClick()
                        }
                    )
                }
            }
        }
    }
//        title = {
//            Text(
//                text = title
//            )
//        },
//
//        dismissButton = {
//            ButtonWithIcon(
//                ButtonType.MEDIUM,
//                R.drawable.clear_icon,
//                onClick = { onDismissClick() }
//            )
//        },
//        confirmButton = {
//            content()
//            ButtonWithIcon(
//                ButtonType.MEDIUM,
//                R.drawable.ok_icon,
//                onClick = {
//                    onConfirmClick()
//                }
//            )
//        }

//    )
}