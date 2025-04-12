package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




    @Composable
    fun CustomOutlinedTextField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String = "",
        placeholder: String = "",
        textSize: TextUnit = 16.sp,
        labelSize: TextUnit = 14.sp,
        placeholderSize: TextUnit = 14.sp,
        textColor: Color = Color.Black,
        labelColor: Color = Color.Gray,
        placeholderColor: Color = Color.LightGray,
        borderColor: Color = Color.Blue,
        focusedBorderColor: Color = Color.Green,
        unfocusedBorderColor: Color = Color.Gray,
        paddingPlaceholder: PaddingValues,
        paddingValue: PaddingValues,
        paddingLable: PaddingValues,
        height: Dp = 56.dp,
        modifier: Modifier = Modifier,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = label, fontSize = labelSize, color = labelColor, modifier = Modifier.padding(paddingLable))
            },
            placeholder = {
                Text(text = placeholder, fontSize = placeholderSize, color = placeholderColor, modifier = Modifier.padding(paddingPlaceholder))
            },
            textStyle = TextStyle(fontSize = textSize, color = textColor),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Green,
                unfocusedIndicatorColor = Color.Gray
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(height)
                .padding(paddingValue)
        )
    }


//@Composable
//fun CustomeTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    modifier: Modifier
//) {
//
//    OutlinedTextField(
//        modifier = modifier,
//        value = value,
//        onValueChange = onValueChange
//    )
//
//}