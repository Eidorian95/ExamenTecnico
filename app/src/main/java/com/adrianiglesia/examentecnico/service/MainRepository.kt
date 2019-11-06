package com.adrianiglesia.examentecnico.service

import com.adrianiglesia.examentecnico.model.Hotel
import com.adrianiglesia.examentecnico.model.response.HotelDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainRepository {

    private val services: ApiServices
    private val retrofit: Retrofit = MyRetrofit.getRetrofit()

    init {
        services = retrofit.create(ApiServices::class.java)
    }

    fun getAllHotels(successHandler: (List<Hotel>)->Unit, failureHandler: (String)-> Unit){
        val hotelsList: Call<HotelDataResponse> = services.getAllHotels()
        hotelsList.enqueue(object : Callback<HotelDataResponse> {
             override fun onFailure(call: Call<HotelDataResponse>, t: Throwable) {
                 failureHandler(t.message.toString())
             }
             override fun onResponse(call: Call<HotelDataResponse>, response: Response<HotelDataResponse>) {
                 if(response.isSuccessful){
                     successHandler(response.body()?.items!!)
                 }else{
                     failureHandler(response.message())
                 }
             }
         })
    }

}
