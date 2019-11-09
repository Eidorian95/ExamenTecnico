package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("good")
    val good: String,
    @SerializedName("bad")
    val bad: String,
    @SerializedName("type")
    val type: String
)