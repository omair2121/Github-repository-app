package com.crazy.coder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crazy.coder.domain.GetRepositoriesUseCase

// can make it generic for every viewmodel, we have only one so using it for better understanding
class GitViewModelFactory(private val useCase: GetRepositoriesUseCase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GithubViewModel(useCase) as T
    }
}