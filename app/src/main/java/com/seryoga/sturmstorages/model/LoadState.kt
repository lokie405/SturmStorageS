package com.seryoga.sturmstorages.model

enum class LoadState(val label: String) {
//    FIRST_LAUNCH("First launch"),
//    LAUNCH("Launch"),
    CONNECTING("Connecting"),
    CONNECTED("Connected"),
    START_LOADING("Start loading"),
    LOADING_ITEM("Loading item"),
    FINISHED_LOAD("Finished load"),
    START_ADD_T0_NEW("Start add to new"),
    FINISH_ADD_T0_NEW("Finish add to new"),
//    NEW_DATA_READY("New data ready to show"),
    NOT_UPDATED_YET_TODAY("Today already SDJFOSOIFJOSAFJOSFJOASIJFOASJF"),
    ALREADY_UPDATED_TODAY("TodayFODJFODSJFOJSDFOSJFJAS"),
    NO_NEED_TO_UPDATE("No need to update"),
    NEED_TO_BE_UPDATE("Need to be update"),
    CHECK_VERSION("Check version"),
//    CURRENT_DATA_UPDATE("Current data update"),
    OLD_DATA_UPDATE("Old data update"),

    ERROR("Error"),
    ERROR_NO_INTERNET("Error no internet connection"),
    ERROR_NO_DATA("Error no data"),

    DENY_AUTOUPDATE("Deny autoupdate")

}