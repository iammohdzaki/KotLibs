package ui

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import data.Category
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
            delay(2000)
            val reposResponse = kotLibApi.getReposData().categories
            _categories.value = reposResponse
            _repoList.value = reposResponse.flatMap { it.repos }
            isLoadingData.value = false
        }
    }

    fun updateSelectedCategory(category: Category) {
        _selectedCategory.value = category
    }

}