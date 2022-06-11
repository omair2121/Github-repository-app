package com.crazy.coder.domain

import com.crazy.coder.data.repos.GitRepositoriesRepo

class GetRepositoriesUseCase (private val repositoriesRepo: GitRepositoriesRepo) {
    suspend operator fun invoke() = repositoriesRepo.getRepositories()
}