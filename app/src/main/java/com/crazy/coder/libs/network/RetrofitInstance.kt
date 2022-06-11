package com.crazy.coder.libs.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private lateinit var instance: Retrofit
    private const val url = "https://gh-trending-api.herokuapp.com"

    @Synchronized
    fun getInstance(): Retrofit {
        if (!RetrofitInstance::instance.isInitialized) {
            instance = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
        }
        return instance
    }
}