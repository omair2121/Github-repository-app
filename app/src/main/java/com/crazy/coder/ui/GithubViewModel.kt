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

class GithubViewModel(private val useCase: GetRepositoriesUseCase): ViewModel(), ViewSelection {
    private var _repoList = MutableLiveData<MutableList<RepositoriesModelItem>>()
    val repoList: LiveData<MutableList<RepositoriesModelItem>>
        get() = _repoList

    fun getRepositories() {
        viewModelScope.launch {
            val result = useCase()
            if(result.isNotEmpty())
                _repoList.value = result
        }
    }

    override fun onViewSelect(clickedItem: RepositoriesModelItem) {
//        _repoList.value?.find {
//            if(it.rank == clickedItem.rank) {
//                it.isSelected = clickedItem.isSelected
//                true
//            }else false
//        }
//        _repoList.notify()
    }
//
//   private fun <T> MutableLiveData<T>.notify() {
//        this.value = (this.value)
//    }
}