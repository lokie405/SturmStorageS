package com.seryoga.sturmstorages.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.seryoga.sturmstorages.R

    /**
     * To add new font:
     * - download regular font
     * - make 1, 2, 3 step to connect font
     * - in DesignPicker add ButtonTextS with new font
     * and set color to active font
     **/

object Font {

    /* 1 */
    const val ROBOTO = "roboto"
    const val JET_BRAIN = "jet_brain"
    const val COMIC_RELIEF ="comic_relief"
    const val SANS_NARROW = "sans_narrow"

    /* 2 */
//    val robotoRegular = FontFamily( Font(R.font.roboto_regular, FontWeight.Normal) )
    val robotoMedium = FontFamily( Font(R.font.roboto_medium, FontWeight.Medium) )
//    val robotoBold = FontFamily( Font(R.font.roboto_bold, FontWeight.Bold) )
    val jetBrainMonoMedium = FontFamily( Font(R.font.jet_brains_mono_medium, FontWeight.Medium) )
    val jetBrainMonoBold = FontFamily( Font(R.font.jet_brain_mono_bold, FontWeight.Bold) )
    val comicReliefRegular = FontFamily(Font(R.font.comic_relief_regular,FontWeight.Medium))
    val sansNarrowRegular = FontFamily(Font(R.font.sans_narrow_regular, FontWeight.Medium))

    /* 3 */
    var mapFontsFamily = mapOf(
        ROBOTO to robotoMedium,
        JET_BRAIN to jetBrainMonoMedium,
        COMIC_RELIEF to comicReliefRegular,
        SANS_NARROW to sansNarrowRegular,
        )
}
