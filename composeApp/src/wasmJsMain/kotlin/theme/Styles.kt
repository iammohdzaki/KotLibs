package theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

object Styles {
    fun TextStyleNormal(size: TextUnit) =
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = SansFont,
            fontSize = size
        )

    fun TextStyleMedium(size: TextUnit) =
        TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = SansFont,
            fontSize = size
        )

    fun TextStyleSemiBold(size: TextUnit) =
        TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontFamily = SansFont,
            fontSize = size
        )

    fun TextStyleBold(size: TextUnit) =
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = SansFont,
            fontSize = size
        )
}