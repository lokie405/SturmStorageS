package com.seryoga.sturmstorages.model

sealed class NavRoutes(val route: String){
    object Main: NavRoutes("main-screen")
    object Setting: NavRoutes("setting-screen")
    }