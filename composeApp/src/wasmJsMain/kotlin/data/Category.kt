package data

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val repos: List<Repository>
)
