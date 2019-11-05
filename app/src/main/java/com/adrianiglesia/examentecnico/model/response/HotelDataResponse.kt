package com.adrianiglesia.examentecnico.model.response


import com.adrianiglesia.examentecnico.model.Hotel
import com.google.gson.annotations.SerializedName

data class HotelDataResponse(val message:String?,
    @SerializedName("items")
    val items: List<Hotel>?
)