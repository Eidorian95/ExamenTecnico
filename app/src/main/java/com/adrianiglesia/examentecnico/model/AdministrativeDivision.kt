package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class AdministrativeDivision(
    @SerializedName("code")
    val code: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)