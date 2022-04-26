package com.example.covidappcompose.model

data class DataSourceResponse(
    val lastGithubCommit: String?,
    val publishedBy: String?,
    val ref: String?,
    val url: String?
)