package com.seryoga.sturmstorages

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.seryoga.sturmstorages.db.ProductViewModel
import com.seryoga.sturmstorages.db.SturmDB
import com.seryoga.sturmstorages.screen.MainScreen


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        setContent {
//      applicationContext.deleteDatabase("sturm_storage.db")

            val db = SturmDB.getInstance(applicationContext)
            val dao = db.dao()
            val viewModel = remember { ProductViewModel(dao) }

            MainScreen(viewModel)
//            runBlocking {
//                LoadProducts(applicationContext, viewModel)
//            }
        }
    }
}