package ui

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import data.Category
import data.RepoData
import data.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import remote.KotLibApi

class KotLibViewModel : KMMViewModel(), KoinComponent {

    private val kotLibApi: KotLibApi by inject()
    val isLoadingData = MutableStateFlow(viewModelScope, true)

    private val _repoData = MutableStateFlow<RepoData?>(viewModelScope, null)
    private val _repoList = MutableStateFlow<List<Repository>>(viewModelScope, emptyList())
    private val _categories = MutableStateFlow<List<Category>>(viewModelScope, emptyList())
    private val _selectedCategory = MutableStateFlow<Category?>(viewModelScope, null)

    @NativeCoroutinesState
    val repoList = _repoList.asStateFlow()

    @NativeCoroutinesState
    val categories = _categories.asStateFlow()

    @NativeCoroutinesState
    val selectedCategory = _selectedCategory.asStateFlow()

    init {
        isLoadingData.value = true
        viewModelScope.coroutineScope.launch {
            delay(100)
            _repoData.value = kotLibApi.getReposDataV2()
            _repoData.value?.let { it ->
                _categories.value = it.categories
                _repoList.value = it.repos
                _selectedCategory.value = it.categories.find { it.categoryId == -1 }
            }
            isLoadingData.value = false
        }
    }

    private fun updateRepoList(category: Category) {
        _repoData.value?.let { it ->
            _repoList.value =
                if (category.categoryId == -1) it.repos else it.repos.filter { it.categoryId == category.categoryId }
        }
    }

    fun updateSelectedCategory(category: Category) {
        _selectedCategory.value = category
        updateRepoList(category)
    }

    fun searchRepo(repoString: String) {
        viewModelScope.coroutineScope.launch {
            if (_repoData.value != null) {
                delay(100)
                _repoList.value = _repoData.value!!.repos.filter { repo ->
                    repo.name.contains(repoString, ignoreCase = true) || repo.description.contains(
                        repoString,
                        ignoreCase = true
                    )
                }
            }
        }
    }
}