package com.abdl.mygithubsearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdl.mygithubsearch.data.remote.Repository

class ViewModelFactory constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory()
    {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            when {
                modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                    return MainViewModel(repository) as T
                }
                else -> throw Throwable("Unknown ViewModel class:" + modelClass.name)
            }
        }
    }