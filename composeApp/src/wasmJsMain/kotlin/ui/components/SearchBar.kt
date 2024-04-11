package ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.Styles

@Preview
@Composable
fun SearchBar(modifier: Modifier, onSearch: (String) -> Unit) {
    var query by remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            textStyle = Styles.TextStyleMedium(16.sp),
            shape = MaterialTheme.shapes.small,
            placeholder = {
                Text("Search Libraries", style = Styles.TextStyleMedium(16.sp))
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.onPrimary
            ),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        )
    }
}