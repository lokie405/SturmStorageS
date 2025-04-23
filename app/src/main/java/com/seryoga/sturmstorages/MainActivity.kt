package com.seryoga.sturmstorages

import SettingStoreManager
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.seryoga.sturmstorages.util.ViewModelProduct
import com.seryoga.sturmstorages.db.SturmDB
import com.seryoga.sturmstorages.model.NavRoutes
import com.seryoga.sturmstorages.model.SettingData
import com.seryoga.sturmstorages.screen.DesignPicker
//import com.seryoga.sturmstorages.model.Screen
import com.seryoga.sturmstorages.screen.MainScreen
import com.seryoga.sturmstorages.screen.SettingScreen
import com.seryoga.sturmstorages.ui.theme.SturmStorageSTheme


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
            val settingStoreManager = SettingStoreManager(applicationContext)
            val settings by settingStoreManager.settingsFlow.collectAsState(SettingData())

            val isDarkTheme by settingStoreManager.getThemeType().collectAsState(true)
            SturmStorageSTheme(
                darkTheme = isDarkTheme
            ) {
                val db = SturmDB.getInstance(applicationContext)
                val dao = db.dao()
                val vmProduct = remember { ViewModelProduct(dao) }
//                val vmSturm = ViewModelSturm()
//            val currentScreen by vmSturm.screen.collectAsStateWithLifecycle()


                val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = NavRoutes.Setting.route
            ) {
                composable(NavRoutes.Main.route){ MainScreen(navController, vmProduct) }
                composable(NavRoutes.Setting.route){ SettingScreen(navController) }
                composable(
                    route = "design-picker/{root}",
                    arguments = listOf(navArgument("root") { type = NavType.StringType })
                ) { backStackEntry ->
                    val root = backStackEntry.arguments?.getString("root") ?: "Unknown"
                    DesignPicker(navController, settings, root)
                }

//                composable(
//                    route = "detail/{target}",
//                    arguments = listOf(navArgument("target") { type = NavType.StringType })
//                ) { backStackEntry ->
//                    val target = backStackEntry.arguments?.getString("target") ?: "Guest"
//                    ColorPicker(navController, settingStoreManager, target)
//                }
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
//
            //    NOTE: if need show keyboard

//                val keyboardController = LocalSoftwareKeyboardController.current
//                keyboardController?.show()

//TestScreen(viewModel = vmProduct)
//            runBlocking {
//                LoadProducts(applicationContext, vmProduct)
//            }

            }
        }
    }
}

