package com.seryoga.sturmstorages.model

sealed class NavRoutes(val route: String){
    object Main: NavRoutes("main-screen")
    object Setting: NavRoutes("setting-screen")
    object RowDisplaySetting: NavRoutes("row-setting")
//    object RowDesign: NavRoutes("row-setting_design______FAKE")
    object DesignPicker: NavRoutes("design-picker")
    }