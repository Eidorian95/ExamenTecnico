package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class GeoLocation(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)