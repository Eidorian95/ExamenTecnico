package com.adrianiglesia.examentecnico.model.response


import com.adrianiglesia.examentecnico.model.Hotel
import com.google.gson.annotations.SerializedName

data class HotelDetailDataResponse(
    @SerializedName("hotel")
    val hotel: Hotel,
    @SerializedName("id")
    val id: String
)