package com.abdl.mygithubsearch.data.remote

import com.abdl.mygithubsearch.data.remote.model.ItemsItem
import com.abdl.mygithubsearch.data.remote.model.SearchUserResponse
import com.abdl.mygithubsearch.data.remote.services.ApiConfig
import retrofit2.Response

class Repository {

    suspend fun getListUser(username: String) : Response<SearchUserResponse>{
        return ApiConfig.getApiService().findUser(username)
    }
}