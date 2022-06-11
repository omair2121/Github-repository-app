package com.crazy.coder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crazy.coder.data.models.RepositoriesModelItem
import com.crazy.coder.domain.GetRepositoriesUseCase
import com.crazy.coder.domain.ViewSelection
import java.util.ArrayList
import kotlinx.coroutines.launch

class GithubViewModel(private val useCase: GetRepositoriesUseCase) : ViewModel(), ViewSelection {
    private var _repoList = MutableLiveData<MutableList<RepositoriesModelItem>?>()
    val repoList: LiveData<MutableList<RepositoriesModelItem>?>
        get() = _repoList

    fun getRepositories() {
        viewModelScope.launch {
            _repoList.value =
                try {
                    val result = useCase()
                    result.ifEmpty { mutableListOf() }
                } catch (e: Exception) {
                    mutableListOf()
                }
        }
    }

    // can be used for future operation on the list
    override fun onViewSelect(item: RepositoriesModelItem) {

    }
}