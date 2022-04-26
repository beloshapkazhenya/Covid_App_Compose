package com.example.covidappcompose.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    companion object {
        private const val BASE_URL: String = "https://coronavirus.m.pipedream.net/"

        internal fun getApi(): Retrofit {
            val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

            return builder.build()
        }
    }
}