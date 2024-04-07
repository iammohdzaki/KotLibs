package data

import kotlinx.serialization.Serializable

@Serializable
data class RepoData(
    val tags: List<String>,
    val categories: List<Category>
)
