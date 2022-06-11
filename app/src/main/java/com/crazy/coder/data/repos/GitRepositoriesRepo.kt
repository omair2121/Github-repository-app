package com.crazy.coder.data.repos

import com.crazy.coder.data.services.GitHubService
import com.crazy.coder.libs.network.RetrofitInstance

class GitRepositoriesRepo {
    private val retrofit = RetrofitInstance.getInstance()
    private val service = retrofit.create(GitHubService::class.java)

    suspend fun getRepositories() = service.getRepositories()

}