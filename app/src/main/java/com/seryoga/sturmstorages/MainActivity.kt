package com.seryoga.sturmstorages

import SettingStoreManager
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.db.SturmDB
import com.seryoga.sturmstorages.model.NavRoutes
//import com.seryoga.sturmstorages.model.Screen
import com.seryoga.sturmstorages.screen.MainScreen
import com.seryoga.sturmstorages.screen.SettingScreen
import com.seryoga.sturmstorages.util.Const
import com.seryoga.sturmstorages.util.Const.TAG
import com.seryoga.sturmstorages.util.ViewModelSturm
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            SettingStoreManager.getDisplayType(applicationContext).collect { displayType ->
//                Log.i(TAG, "---Start data store: $displayType");
//            }
//        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        setContent {

            val db = SturmDB.getInstance(applicationContext)
            val dao = db.dao()
            val vmProduct = remember { ViewModelProduct(dao) }
            val vmSturm = ViewModelSturm()
//            val currentScreen by vmSturm.screen.collectAsStateWithLifecycle()

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = NavRoutes.Main.route
            ) {
                composable(NavRoutes.Main.route){ MainScreen(navController, vmProduct) }
                composable(NavRoutes.Setting.route){ SettingScreen() }
                }
//            val showing by vmSturm.showingScreen.collectAsStateWithLifecycle(Const.MAIN_SCREEN)

//            lifecycleScope.launch {
//                SettingStoreManager.saveDisplayType(applicationContext, Const.DISPLAY_TYPE_ALL_IN_ROW)
//            }

//            lifecycleScope.launch {
//                SettingStoreManager.getDisplayType(applicationContext).collect { displayType ->
//                    Log.i(TAG, "---After data store: $displayType");
//                }
//            }

//            when(currentScreen) {
//                Screen.MAIN_SCREEN -> {
//                    MainScreen(vmProduct)
//                    Log.i(TAG, "((((((MAIN");
//                }
//                Screen.SETTING_SCREEN-> {
//                    SettingScreen()
//                    Log.i(TAG, "((((((Setting");
//                }
//            }
            val keyboardController = LocalSoftwareKeyboardController.current
            keyboardController?.show()

//TestScreen(viewModel = vmProduct)
//            runBlocking {
//                LoadProducts(applicationContext, vmProduct)
//            }

        }
    }
}

