package com.example.covidappcompose.net

import com.example.covidappcompose.model.DataResponse
import retrofit2.Call

class DataRepository(private val service: DataRepositoryService) {

    fun getData(): Call<DataResponse> {
        return service.getData()
    }
}