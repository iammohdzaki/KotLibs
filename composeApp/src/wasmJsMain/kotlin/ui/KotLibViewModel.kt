package ui

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import data.Repository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import remote.KotLibApi

class KotLibViewModel : KMMViewModel(), KoinComponent {

    private val kotLibApi: KotLibApi by inject()
    val isLoadingData = MutableStateFlow(viewModelScope, true)

    private val _repoList = MutableStateFlow<List<Repository>>(viewModelScope, emptyList())

    @NativeCoroutinesState
    val repoList = _repoList.asStateFlow()

    init {
        isLoadingData.value = true
        viewModelScope.coroutineScope.launch {
            _repoList.value = kotLibApi.getReposData().categories.flatMap { it.repos }
            isLoadingData.value = false
        }
    }

}