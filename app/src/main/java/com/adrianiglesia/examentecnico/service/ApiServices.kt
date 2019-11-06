package com.adrianiglesia.examentecnico.service

import com.adrianiglesia.examentecnico.model.response.HotelDataResponse
import com.adrianiglesia.examentecnico.model.response.HotelDetailDataResponse

import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @GET("hotels")
    fun getAllHotels(): Call<HotelDataResponse>

    @GET("hotels/{id}")
    fun getHotelDetail(@Path("id") id:String): Call<HotelDetailDataResponse>

}
