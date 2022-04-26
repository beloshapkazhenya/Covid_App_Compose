package com.example.covidappcompose.model

data class DataResponse(
    val apiSourceCode: String?,
    val cache: CacheResponse?,
    val dataSource: DataSourceResponse?,
    val rawData: List<RawDataResponse>?,
    val summaryStats: SummaryStatsResponse?
)