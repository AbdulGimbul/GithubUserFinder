package com.abdl.mygithubsearch.data.remote.services

import com.abdl.mygithubsearch.data.remote.model.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    @Headers("Authorization: token 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")
    suspend fun findUser(
        @Query("q") username: String
    ): Response<SearchUserResponse>
}