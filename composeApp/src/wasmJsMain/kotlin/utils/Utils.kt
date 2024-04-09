package utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import theme.SansFont

suspend fun loadSansFont() {
    SansFont = FontFamily(
        Font(identity = "SansRegular", data = loadResource("fonts/sans_regular.ttf"), weight = FontWeight.Light),
        Font(identity = "SansThin", data = loadResource("fonts/sans_thin.ttf"), weight = FontWeight.Normal),
        Font(identity = "SansMedium", data = loadResource("fonts/sans_medium.ttf"), weight = FontWeight.Medium),
        Font(identity = "SansBold", data = loadResource("fonts/sans_bold.ttf"), weight = FontWeight.Bold)
    )
}

internal suspend fun loadResource(path: String): ByteArray {
    return loadImage(path).toByteArray()
}