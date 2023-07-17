package com.example.openinassignment.Adapters

import com.example.openinassignment.ApiService
import com.example.openinassignment.Data.Hello
import retrofit2.Response

class Repository(private val retroifit:ApiService) {
    suspend fun getResponse():Response<Hello>
    {
        return retroifit.getDetails()
    }
}