package com.example.covidappcompose.net

import com.example.covidappcompose.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface DataRepositoryService {
    @GET("https://coronavirus.m.pipedream.net/")
    fun getData(): Call<DataResponse>
}