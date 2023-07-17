package com.example.openinassignment

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitObject {
    const val Base_url="https://api.inopenapp.com/"

    fun getRetrofitObj(okHttpClient: OkHttpClient):Retrofit
    {
        return Retrofit.Builder().baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }
}