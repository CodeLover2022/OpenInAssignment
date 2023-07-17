package com.example.openinassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.openinassignment.Adapters.Repository

class viewModelFactory(private val repo: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelRetro(repo) as T
    }
}