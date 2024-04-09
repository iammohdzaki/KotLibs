import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import utils.loadSansFont

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        var loadingResources: Boolean by remember { mutableStateOf(true) }

        if (loadingResources) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            App()
        }
        LaunchedEffect(Unit) {
            loadSansFont()
            loadingResources = false
        }
    }
}