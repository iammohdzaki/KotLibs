package ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import theme.Styles

class RepoListScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinInject<KotLibViewModel>()
        val isLoadingData by viewModel.isLoadingData.collectAsState()

        Scaffold(
            topBar = {
                Text(
                    text = "KotLibs",
                    style = Styles.TextStyleMedium(25.sp),
                    fontWeight = FontWeight.Medium
                )
            }
        ){
            Text(
                if(isLoadingData) "LOADING" else "${viewModel.repoList.value.map { it.name }}",
                style = Styles.TextStyleMedium(16.sp),
                fontWeight = FontWeight.Medium
            )
        }
    }
}