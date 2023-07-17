package com.example.openinassignment

import androidx.lifecycle.ViewModel
import com.example.openinassignment.Adapters.Repository
import com.example.openinassignment.Data.Hello
import retrofit2.Response

class ViewModelRetro(private val repo:Repository):ViewModel() {
    suspend fun getData():Response<Hello>
    {
        return repo.getResponse()
    }
}