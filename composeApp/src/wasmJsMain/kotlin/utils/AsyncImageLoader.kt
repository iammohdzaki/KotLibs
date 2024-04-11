package utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale

val imagesCache = mutableMapOf<String, ImageBitmap>()

@Composable
fun AsyncImageLoader(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier
) {

    var bitmap: ImageBitmap? by remember { mutableStateOf(null) }

    if (bitmap != null) {
        Image(bitmap!!, contentDescription = contentDescription, modifier = modifier, contentScale = ContentScale.Crop)
    }

    LaunchedEffect(imageUrl) {
        if (imagesCache.contains(imageUrl)) {
            bitmap = imagesCache[imageUrl]!!
        } else {
            val arrayBuffer = loadImage(imageUrl)
            val skiaImg = org.jetbrains.skia.Image.makeFromEncoded(arrayBuffer.toByteArray())
            imagesCache[imageUrl] = skiaImg.toComposeImageBitmap()
            bitmap = imagesCache[imageUrl]
        }
    }
}