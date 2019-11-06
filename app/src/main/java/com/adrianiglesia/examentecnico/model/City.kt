package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("administrative_division")
    val administrativeDivision: AdministrativeDivision,
    @SerializedName("code")
    val code: String,
    @SerializedName("country")
    val country: Country,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)