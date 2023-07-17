package com.example.openinassignment

import com.example.openinassignment.Data.TopLink
import com.example.openinassignment.Data.Hello
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("api/v1/dashboardNew")
    suspend fun getDetails():Response<Hello>


}