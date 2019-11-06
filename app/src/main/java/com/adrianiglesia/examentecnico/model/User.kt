package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("country")
    val country: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("name")
    val name: String
)