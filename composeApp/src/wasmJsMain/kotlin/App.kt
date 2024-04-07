import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import di.commonModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import ui.RepoListScreen

@Preview
@Composable
fun App() {
    KoinApplication(application = {
        modules(commonModule())
    }) {
        MaterialTheme {
            Navigator(screen = RepoListScreen())
        }
    }
}