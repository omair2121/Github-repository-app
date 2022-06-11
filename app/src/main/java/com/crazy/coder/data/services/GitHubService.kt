package com.crazy.coder.data.services

import com.crazy.coder.data.models.RepositoriesModelItem
import retrofit2.http.GET

interface GitHubService {
    @GET("/repositories")
    suspend fun getRepositories(): MutableList<RepositoriesModelItem>
}