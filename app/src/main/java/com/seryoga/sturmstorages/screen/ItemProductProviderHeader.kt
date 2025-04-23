package com.seryoga.sturmstorages.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seryoga.sturmstorages.db.Product
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.ui.theme.Cardboard
import com.seryoga.sturmstorages.ui.theme.Cornsilk
import com.seryoga.sturmstorages.ui.theme.Dollar
import com.seryoga.sturmstorages.ui.theme.Font


@Composable
fun ItemProductProviderHeader(settings: SettingData, product: Product) {

    Card(
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.6f),
                text = product.name,
                fontFamily = Font.jetBrainMonoMedium,
                fontSize = 14.sp,
                color = Cornsilk,
                maxLines = 3,
            )
//        Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center,


                ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .fillMaxHeight(),
                    textAlign = TextAlign.Center,
                    text = product.quantity.replace(".000", ""),
                    fontFamily = Font.jetBrainMonoMedium,
                    fontSize = 14.sp,
                    color = Cardboard
                )
            }
//        Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center

            ) {1
                Text(
                    modifier = Modifier,
                    textAlign = TextAlign.End,
                    text = product.price.replace("грн.", if(settings.hryvniaSign) "₴" else ""),
                    fontFamily = Font.jetBrainMonoMedium,
                    fontSize = 14.sp,
                    color = Dollar
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(5.dp)
//            .background(co)

        )
    }

}


@Preview(showBackground = true)
@Composable
fun Prev() {
//    ItemProductProviderHeader()
}