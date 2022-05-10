package com.abdl.mygithubsearch.data.remote.services

import com.abdl.mygithubsearch.data.remote.model.ItemsItem
import com.abdl.mygithubsearch.data.remote.model.SearchUserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    @Headers("Authorization: token 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")
    suspend fun findUser(
        @Query("q") username: String
    ): Response<SearchUserResponse>
}