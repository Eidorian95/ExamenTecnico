package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("code")
    val code: String,
    @SerializedName("mask")
    val mask: String,
    @SerializedName("ratio")
    val ratio: Double
)