package com.seryoga.sturmstorages.model

enum class LoadStatus(val label: String) {
    CONNECTING("Connecting"),
    CONNECTED("Connected"),
    START_LOADING("Start loading"),
    LOADING_ITEM("Loading item"),
    FINISHED("Finished"),
    ERROR("Error")

}