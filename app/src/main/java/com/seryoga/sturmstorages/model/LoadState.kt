package com.seryoga.sturmstorages.model

enum class LoadState(val label: String) {
    CONNECTING("Connecting"),
    CONNECTED("Connected"),
    START_LOADING("Start loading"),
    LOADING_ITEM("Loading item"),
    FINISHED_LOAD("Finished load"),
    START_ADD_T0_NEW("Start add to new"),
    FINISH_ADD_T0_NEW("Finish add to new"),
    NEW_DATA_READY("New data ready to show"),
    ERROR("Error"),
    ERROR_NO_INTERNET("Error no internet connection"),
    ERROR_NO_DATA("Error no data"),

}