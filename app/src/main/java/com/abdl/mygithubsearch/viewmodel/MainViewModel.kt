package com.abdl.mygithubsearch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdl.mygithubsearch.data.remote.Repository
import com.abdl.mygithubsearch.data.remote.model.ItemsItem
import com.abdl.mygithubsearch.data.remote.model.SearchUserResponse
import com.abdl.mygithubsearch.data.remote.services.ApiConfig
import com.abdl.mygithubsearch.data.remote.services.ApiService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val repository: Repository) : ViewModel() {
//    private val _listUser = MutableLiveData<ArrayList<ItemsItem>>()
    val listUser = MutableLiveData<ArrayList<ItemsItem>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var myListUser: MutableLiveData<Response<SearchUserResponse>> = MutableLiveData()

    fun getListUser(username: String){
        viewModelScope.launch {
            val response = repository.getListUser(username)
            myListUser.value = response
        }
    }

//    fun setUser(username: String){
//        _isLoading.value = true
//        val listItems = ArrayList<ItemsItem>()
//
//        val client = ApiConfig.getApiService().findUser(username)
//        client.enqueue(object : Callback<SearchUserResponse>{
//            override fun onResponse(
//                call: Call<SearchUserResponse>,
//                response: Response<SearchUserResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful){
//                    val result = response.body()?.items
//                    listItems.add(result)
//                    Log.d("MainViewModel", "CekListUser ${_listUser.value}")
//                } else {
//                    Log.e("MainViewModel", "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<SearchUserResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e("MainViewModel", "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
}