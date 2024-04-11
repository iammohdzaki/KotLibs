package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.http.*
import org.koin.compose.koinInject
import theme.Strings
import theme.Styles
import ui.components.CategoryView
import ui.components.SearchBar

class RepoHomePage : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinInject<KotLibViewModel>()
        val isLoadingData by viewModel.isLoadingData.collectAsState()
        val selectedCategory by viewModel.selectedCategory.collectAsState()
        val categories by viewModel.categories.collectAsState()
        val repos by viewModel.repoList.collectAsState()

        if (isLoadingData) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        } else {
            Scaffold(
                topBar = {
                    Row(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.primaryVariant
                            )
                            .padding(start = 24.dp, top = 10.dp, bottom = 10.dp)
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        KamelImage(
                            resource = asyncPainterResource(
                                data = Url("drawable/logo.png")
                            ),
                            modifier = Modifier.width(30.dp).height(30.dp),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = Strings.APP_NAME,
                            style = Styles.TextStyleSemiBold(25.sp),
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row {
                        Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primary)) {
                            Spacer(modifier = Modifier.weight(0.2f))
                            SearchBar(modifier = Modifier.weight(0.5f).padding(vertical = 30.dp)) { searchString ->
                                viewModel.searchRepo(searchString)
                            }
                            Spacer(modifier = Modifier.weight(0.2f))
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.weight(0.2f)
                                .padding(12.dp),
                        ) {
                            Text(
                                text = Strings.CATEGORIES,
                                style = Styles.TextStyleBold(16.sp),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            LazyColumn {
                                items(categories) { category ->
                                    CategoryView(
                                        category,
                                        category.categoryId == (selectedCategory?.categoryId ?: -1)
                                    ) {
                                        viewModel.updateSelectedCategory(it)
                                    }
                                }
                            }
                            Divider(color = Color.Gray, thickness = 1.dp)
                        }
                        Column(
                            modifier = Modifier.weight(0.8f)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = Strings.Libraries,
                                style = Styles.TextStyleBold(16.sp),
                                fontWeight = FontWeight.Bold
                            )
                            LazyColumn {
                                items(repos) { repo ->
                                    Text(repo.name)
                                    Spacer(modifier = Modifier.size(10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}