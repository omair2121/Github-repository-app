package com.crazy.coder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazy.coder.data.models.RepositoriesModelItem
import com.crazy.coder.domain.GetRepositoriesUseCase
import com.crazy.coder.domain.ViewSelection
import kotlinx.coroutines.launch

class GithubViewModel(private val useCase: GetRepositoriesUseCase) : ViewModel(), ViewSelection {
    private var _repoList = MutableLiveData<MutableList<RepositoriesModelItem>?>()
    val repoList: LiveData<MutableList<RepositoriesModelItem>?>
        get() = _repoList

    private val _repoOriginalList = MutableLiveData<MutableList<RepositoriesModelItem>?>()

    fun getRepositories() {
        viewModelScope.launch {
            _repoList.value =
                try {
                    val result = useCase()
                    result.ifEmpty { mutableListOf() }
                } catch (e: Exception) {
                    mutableListOf()
                }
            _repoOriginalList.value = _repoList.value
        }
    }

    // can be used for future operation on the list
    override fun onViewSelect(item: RepositoriesModelItem) {

    }

    fun search(query: String) {
        reset()
        if(!query.isNullOrBlank()) {
            val filtered = _repoList.value?.filter { it.username.contains(query, ignoreCase = true) }
            _repoList.value = filtered?.toMutableList()
        }
    }

    fun reset() {
        _repoList.value = _repoOriginalList.value
    }

    fun isOriginalListAvailable() = _repoOriginalList.value?.isNotEmpty() == true

}