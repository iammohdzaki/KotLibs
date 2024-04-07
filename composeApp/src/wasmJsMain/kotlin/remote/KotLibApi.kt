package remote

import com.zaki.kotlibs.BuildKonfig
import data.RepoData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class KotLibApi(
    private val client: HttpClient,
    private val reposUrl: String = BuildKonfig.REPOS_URL
) {
    suspend fun getReposData() = client.get(reposUrl).body<RepoData>()
}