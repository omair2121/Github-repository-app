package com.crazy.coder.data.models

data class RepositoriesModelItem(
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val rank: Int,
    val repositoryName: String,
    val since: String,
    val starsSince: Int,
    val totalStars: Int,
    val url: String,
    val username: String,
    var isSelected: Boolean = false
)