package ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import data.Category
import theme.Styles

@Composable
fun CategoryView(category: Category, isSelected: Boolean, onSelect: (Category) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onSelect.invoke(category) }
        )
        Text(
            text = category.name,
            style = Styles.TextStyleMedium(16.sp),
            fontWeight = FontWeight.Medium
        )
    }
}