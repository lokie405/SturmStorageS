package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.db.ProductViewModel
import com.seryoga.sturmstorages.ui.theme.ColorGreen
import com.seryoga.sturmstorages.ui.theme.Font

@Composable
fun TopBar(viewModel: ProductViewModel) {

    var expanded by remember { mutableStateOf(false) }
    var chosenProvider by remember { mutableStateOf("Постачальники") }
    val listOfProviders by viewModel.providers.observeAsState(initial = emptyList())
//    val listOfProviders by remember { mutableStateOf(viewModel.getProvider()) }
//    Log.i(TAG, "--MainScreen: LIST OF $listOfProviders")

//    ) {
    Row(
        modifier = Modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.2f)
//                    .fillMaxHeight(),
            ,
//          .weight(0.2f)
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "22/12",
                fontSize = 16.sp,
                fontFamily = Font.jetBrainMonoBold,
                color = ColorGreen
            )
        }
        Box(
            modifier = Modifier,
        )
        Button(onClick = { expanded = true }) {
            Text(chosenProvider)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOfProviders.forEach { provider ->
                DropdownMenuItem(
                    text = { Text(provider) },
                    onClick = {
                        chosenProvider = provider
                        expanded = false
//                            Log.i(TAG, "--MainScreen: CHOSEN : $chosenProvider")
                        if (chosenProvider.equals("Постачальники")) chosenProvider = "ЧАС"
                        viewModel.providerFilter(chosenProvider)
                    }
                )
            }
        }
        Box() {
            Image(
                painter = painterResource(id = R.drawable.choose_all),
                contentDescription = "Select All",
                modifier = Modifier
//                        .fillMaxHeight()
                    .clickable {
                        chosenProvider = "Постачальники"
                        viewModel.providerFilter("%")

                    },

                )
        }

    }
}