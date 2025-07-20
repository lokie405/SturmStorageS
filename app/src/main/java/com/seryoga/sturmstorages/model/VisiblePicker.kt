package com.seryoga.sturmstorages.model

data class VisiblePicker (
    val colorPicker: Boolean = false,
    var fontSizePicker: Boolean = false,
    var fontFamilyPicker: Boolean = false,
    var decorationPicker: Boolean = false,
    var colorOfProviderSecondPicker: Boolean = false,
    var colorOfRowBackgroundPicker: Boolean = false,
    var colorOfRowBackgroundActivePicker: Boolean = false,
    var colorOfProviderBackgroundPicker: Boolean = false,
    var colorOfHighlightBackgroundPicker: Boolean = false,
)