package com.adrianiglesia.examentecnico.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adrianiglesia.examentecnico.model.Hotel
import com.adrianiglesia.examentecnico.model.Review
import com.adrianiglesia.examentecnico.model.response.HotelDetailDataResponse
import com.adrianiglesia.examentecnico.service.DetailRepository

class HotelDetailViewModel:ViewModel() {

    private val repository= DetailRepository()
    private val hotel:MutableLiveData<HotelDetailDataResponse> = MutableLiveData()
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val reviews:MutableLiveData<List<Review>> = MutableLiveData()

    fun getHotelDetail(id:String):LiveData<HotelDetailDataResponse>{
        loadDetail(id)
        return hotel
    }

    fun getReviews():LiveData<List<Review>>{
        return reviews
    }

    fun getLoading():LiveData<Boolean>{
        return isLoading
    }
    private fun loadDetail(id:String){
        isLoading.value = true
        repository.getHotelDetail(id,{
            hotel.value = it
            reviews.value = it.hotel.reviews
            isLoading.value = false
        },{
            isLoading.value = false
            //error
        })
    }
}