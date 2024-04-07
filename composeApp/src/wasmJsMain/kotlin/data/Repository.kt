package data

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val name: String,
    val url: String,
    val description: String,
    val stars: String?,
    val tags: List<String>
)
