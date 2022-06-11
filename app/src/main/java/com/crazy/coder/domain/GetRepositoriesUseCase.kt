package com.crazy.coder.domain

import com.crazy.coder.data.models.RepositoriesModelItem
import com.crazy.coder.data.repos.GitRepositoriesRepo
import com.crazy.coder.libs.network.RequestResult

class GetRepositoriesUseCase (private val repositoriesRepo: GitRepositoriesRepo) {
    suspend operator fun invoke() = repositoriesRepo.getRepositories()
}