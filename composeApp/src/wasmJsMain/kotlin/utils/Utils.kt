package utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import theme.SansFont

suspend fun loadSansFont() {
    SansFont = FontFamily(
        Font(identity = "SansRegular", data = loadResource("fonts/sans_regular.ttf"), weight = FontWeight.Normal),
        Font(identity = "SansThin", data = loadResource("fonts/sans_thin.ttf"), weight = FontWeight.Thin),
        Font(identity = "SansMedium", data = loadResource("fonts/sans_medium.ttf"), weight = FontWeight.Medium),
        Font(identity = "SansBold", data = loadResource("fonts/sans_bold.ttf"), weight = FontWeight.Bold)
    )
}

internal suspend fun loadResource(path: String): ByteArray {
    return loadImage(path).toByteArray()
}

@Composable
fun Modifier.verticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp,
    hideAutomatically: Boolean = false
): Modifier {

    val alpha = if (hideAutomatically) {
        val targetAlpha = if (state.isScrollInProgress) 1f else 0f
        val duration = if (state.isScrollInProgress) 150 else 500
        animateFloatAsState(
            targetValue = targetAlpha,
            animationSpec = tween(durationMillis = duration)
        ).value
    } else {
        1f // Force alpha to 1 for constant visibility
    }

    return drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = firstVisibleElementIndex != null // Draw always if content exists

        // Draw scrollbar if Lazy Column has content
        if (needDrawScrollbar) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex!! * elementHeight // Use !! for non-null
            val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRect(
                color = Color.Gray,
                topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                alpha = alpha
            )
        }
    }
}

object Utils {
    fun openInNewTab(url: String) {
        window.open(url, "_blank")
    }
}