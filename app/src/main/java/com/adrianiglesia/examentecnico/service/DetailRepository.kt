package com.adrianiglesia.examentecnico.service

import com.adrianiglesia.examentecnico.model.response.HotelDetailDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DetailRepository {

    private val services: ApiServices
    private val retrofit: Retrofit = MyRetrofit.getRetrofit()

    init {
        services = retrofit.create(ApiServices::class.java)
    }

    fun getHotelDetail(id:String, successHandler: (HotelDetailDataResponse)->Unit, failureHandler: (String)-> Unit){
        val hotelsList: Call<HotelDetailDataResponse> = services.getHotelDetail(id)
        hotelsList.enqueue(object : Callback<HotelDetailDataResponse> {
            override fun onFailure(call: Call<HotelDetailDataResponse>, t: Throwable) {
                failureHandler(t.message.toString())
            }
            override fun onResponse(call: Call<HotelDetailDataResponse>, response: Response<HotelDetailDataResponse>) {
                if(response.isSuccessful){
                    successHandler(response.body()!!)
                }else{
                    failureHandler(response.message())
                }
            }
        })
    }
}