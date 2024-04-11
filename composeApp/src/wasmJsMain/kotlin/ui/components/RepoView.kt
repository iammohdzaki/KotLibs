package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Repository
import theme.Styles

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RepoView(repository: Repository, onSelect: (Repository) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {
                onSelect.invoke(repository)
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = repository.name,
                style = Styles.TextStyleBold(20.sp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(4.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(repository.tags) { tags ->
                    Chip(
                        shape = RoundedCornerShape(4.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        onClick = {}) {
                        Text(
                            tags,
                            style = Styles.TextStyleMedium(14.sp),
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = repository.description,
                style = Styles.TextStyleMedium(14.sp),
                fontWeight = FontWeight.Medium,
                maxLines = 3
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}