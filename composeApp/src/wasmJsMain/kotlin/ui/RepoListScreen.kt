package ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

class RepoListScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinInject<KotLibViewModel>()
        val isLoadingData by viewModel.isLoadingData.collectAsState()

        Scaffold(
            topBar = {
                Text("KotLib")
            }
        ){
            Text(
                if(isLoadingData) "LOADING" else "${viewModel.repoList.value.map { it.name }}"
            )
        }
    }
}