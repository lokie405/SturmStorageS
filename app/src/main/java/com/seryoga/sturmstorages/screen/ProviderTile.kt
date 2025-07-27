package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.ui.theme.ColorMagenta
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.util.ViewModelSturm

@Composable
fun ProviderTile(
    vmSturm: ViewModelSturm,
    vmProduct: ViewModelProduct,
    listOfProvider: List<String>,
    searchedProviderCallback: (str: String) -> Unit,
) {
//    var isShowProviderList by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
//            .then(
//                if (vmSturm.isShowProviderList) Modifier.fillMaxHeight()
//                else Modifier.height(Const.TOP_BAR_HEIGHT)
//            )

    ) {
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(Const.TOP_BAR_HEIGHT)
                .fillMaxWidth()
                .border(1.dp, ColorMagenta, RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp),
                text = "Provider",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary

            )
        }
    }
}