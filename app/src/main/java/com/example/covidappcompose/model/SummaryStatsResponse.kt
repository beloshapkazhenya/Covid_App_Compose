package com.example.covidappcompose.model

data class SummaryStatsResponse(
    val china: ChinaResponse?,
    val global: GlobalResponse?,
    val nonChina: NonChinaResponse?
)