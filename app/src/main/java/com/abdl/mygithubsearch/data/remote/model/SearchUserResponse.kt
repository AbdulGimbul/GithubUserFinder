package com.abdl.mygithubsearch.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null,
)

data class ItemsItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("login")
	val login: String? = null
)
