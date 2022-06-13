package com.crazy.coder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazy.coder.data.models.Repositories
import com.crazy.coder.domain.GetRepositoriesUseCase
import com.crazy.coder.domain.ViewSelection
import kotlinx.coroutines.launch

class GithubViewModel(private val useCase: GetRepositoriesUseCase) : ViewModel(), ViewSelection {
    // backing field for showing in recyclerver
    private var _repoList = MutableLiveData<MutableList<Repositories>?>()
    val repoList: LiveData<MutableList<Repositories>?>
        get() = _repoList

    // duplicate list for reference in search functionality
    private val _repoOriginalList = MutableLiveData<MutableList<Repositories>?>()

    // fetching repo list data from network
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
    override fun onViewSelect(item: Repositories) {

    }

    fun search(query: String) {
        reset()
        if(!query.isNullOrBlank()) {
            val filtered = _repoList.value?.filter { it.username.contains(query, ignoreCase = true) }
            _repoList.value = filtered?.toMutableList()
        }
    }

    // when we already have repo list available , then just add it to the duplicate list , used in search reset also
    fun reset() {
        _repoList.value = _repoOriginalList.value
    }

    // checking if we already made a network call,
    fun isOriginalListAvailable() = _repoOriginalList.value?.isNotEmpty() == true

}