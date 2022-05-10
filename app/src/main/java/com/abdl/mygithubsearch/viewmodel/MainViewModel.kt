package com.abdl.mygithubsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdl.mygithubsearch.data.remote.Repository
import com.abdl.mygithubsearch.data.remote.model.SearchUserResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()

    var myListUser: MutableLiveData<Response<SearchUserResponse>> = MutableLiveData()

    fun getListUser(username: String){
        viewModelScope.launch {
            val response = repository.getListUser(username)
            myListUser.value = response
        }
    }
}