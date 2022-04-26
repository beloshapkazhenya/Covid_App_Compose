package com.example.covidappcompose.model

data class CacheResponse(
    val expires: String?,
    val expiresTimestamp: Long?,
    val lastUpdated: String?,
    val lastUpdatedTimestamp: Long?
)