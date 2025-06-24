package com.seryoga.sturmstorages.model

sealed class NavRoutes(val route: String){
    object Main: NavRoutes("main-screen")
    object Setting: NavRoutes("setting-screen")
    object RowDesign: NavRoutes("row-setting")
    object DesignPicker: NavRoutes("design-picker")
//    {
//        fun passRoot(root: String): String{
//            return "design-picker/${root}"
//        }
//    }
    }