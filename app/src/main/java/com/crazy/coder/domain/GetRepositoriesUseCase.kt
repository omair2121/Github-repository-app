package com.crazy.coder.domain

import com.crazy.coder.data.repos.GitRepositoriesRepo

// usecase for fetching repository list
class GetRepositoriesUseCase (private val repositoriesRepo: GitRepositoriesRepo) {
    suspend operator fun invoke() = repositoriesRepo.getRepositories()
}