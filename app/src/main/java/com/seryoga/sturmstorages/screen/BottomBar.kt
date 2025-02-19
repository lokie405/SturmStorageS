package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.db.ProductViewModel
import com.seryoga.sturmstorages.ui.theme.ColorBlue
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.ui.theme.Font
import com.seryoga.sturmstorages.ui.theme.MainColor

@Composable
fun BottomBar(viewModel: ProductViewModel) {

    var product by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainColor),
//                .padding(bottom = WindowInsets.ime.asPaddingValues().calculateBottomPadding())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
            .fillMaxWidth()
//                .background(Color.Cyan)
        ) {
            Box(
                modifier = Modifier
//                    .background(Color.Yellow)
                ,
                contentAlignment = Alignment.TopCenter
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(start = 10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = ColorMagenta,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),

                    value = product,
                    onValueChange = {
                        product = it
                        viewModel.productFilter(product)
                    },
                    textStyle = TextStyle( fontFamily = Font.jetBrainMonoMedium),
                    label = {
                        Text(
                            text = "Назва товару",
                            fontFamily = Font.jetBrainMonoBold
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 16.sp
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            product = ""
                            viewModel.productFilter("")
                        }) {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 6.dp),
                                tint = ColorMagenta,
                                painter = painterResource(R.drawable.close),
                                contentDescription = "Clear product",
                            )
                        }
                    }
                )
            }
            IconButton(
                        modifier = Modifier
                            .padding(end = 20.dp, top = 6.dp),
                onClick = {}
            ) {

                Icon(
                    modifier = Modifier
//                        .height(24.dp)
                    ,

                    tint = ColorBlue,
                    painter = painterResource(R.drawable.reset),
                    contentDescription = "Clear product"
                )
            }
        }
    }
}