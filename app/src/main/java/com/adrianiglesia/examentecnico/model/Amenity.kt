package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class Amenity(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String
)