package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlibs.composeapp.generated.resources.Res
import kotlibs.composeapp.generated.resources.no_result
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import theme.Strings
import theme.Styles

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyRepoView(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.no_result),
            modifier = Modifier.width(200.dp).height(200.dp),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(14.dp))
        Text(
            text = Strings.NO_RESULT_FOUND,
            style = Styles.TextStyleSemiBold(25.sp),
            fontWeight = FontWeight.SemiBold,
        )
    }
}