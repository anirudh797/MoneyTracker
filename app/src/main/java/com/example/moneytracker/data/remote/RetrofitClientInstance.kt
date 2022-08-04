package com.example.moneytracker.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {

    lateinit var retrofit: Retrofit


    companion object {
        val BASE_URL = "http://0.0.0.0:8080"
        fun getInstance(): Retrofit {
            return retrofit2.Retrofit.Builder(
            ).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }

    }
}