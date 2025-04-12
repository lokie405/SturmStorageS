package com.seryoga.sturmstorages.screen

import SettingStoreManager
import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.preferencesDataStoreFile
import com.seryoga.sturmstorages.R
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.ui.theme.ColorLightGrey
import com.seryoga.sturmstorages.ui.theme.MainColorDark
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SettingScreen() {

    val context = LocalContext.current
    val settingData by remember {
        mutableStateOf(SettingData())
    }
    val coroutineScope = rememberCoroutineScope()
//    val settingStoreManager = SettingStoreManager(context)

    coroutineScope.launch {
        SettingStoreManager.getSetting(context).collect { store ->
            settingData.displayType = store.displayType
        }
    }

    Card(
        modifier = Modifier.background(MainColorDark)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingTitleMain()
            SettingTitleSpacer(10)
            SettingTitle(stringResource(R.string.setting_display))
            SettingItemTop(
                painterResource(R.drawable.setting_display_type_icon),
                stringResource(R.string.setting_display_type),
                settingData.displayType.toString(),
            ) {

            }
        }
    }
}


@Composable
fun SettingTitleMain() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.setting_title),
//        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SettingTitle(title: String) {
    Text(
        text = title,
//        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = MainColorDark
    )
}

@Composable
fun SettingItem(
    icon: Painter,
    title: String,
    chosen: String,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, title)
        Spacer(Modifier.width(10.dp))
        Column() {
            Text(
                text = title,
                fontSize = 16.sp
            )
            Text(
                text = chosen,
                fontSize = 14.sp

            )
        }
        content()
    }
}

//@Composable
//fun SettingItem(icon: Painter, title: String, content: @Composable () -> Unit) {
//    Row(
//        Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(icon, title)
//        Spacer(Modifier.width(10.dp))
//        Column()
//        Text(
//            text = title,
//            fontSize = 16.sp
//        )
//        content()
//    }
//}

//@Composable
//fun SettingChose(name: String, onClick: () -> Unit) {
//    IconButton(onClick = onClick) {
//        Text(name)
//    }
//}

@Composable
fun SettingItemTop(
    icon: Painter,
    title: String,
    chosen: String = "",
    content: @Composable () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .fillMaxWidth()
    ) {
        SettingItem(icon, title, chosen, content, onClick)
    }
}


@Composable
fun SettingTitleSpacer(spacer: Int, color: Color = ColorLightGrey) {
    Spacer(modifier = Modifier.height(spacer.dp))
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    SettingScreen()
}