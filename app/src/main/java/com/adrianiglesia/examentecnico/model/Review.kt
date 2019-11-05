package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("comments")
    val comments: Comments,
    @SerializedName("user")
    val user: User
)