import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import di.commonModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import theme.KotLibTheme
import ui.RepoHomePage

@Preview
@Composable
fun App() {
    KoinApplication(application = {
        modules(commonModule(enableNetworkLogs = true))
    }) {
        KotLibTheme {
            Navigator(screen = RepoHomePage())
        }
    }
}