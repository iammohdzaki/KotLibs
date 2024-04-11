package data

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val categoryId: Int,
    val name: String,
    val url: String,
    val description: String,
    val stars: String?,
    val tags: List<String>
)
