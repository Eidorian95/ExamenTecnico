package com.adrianiglesia.examentecnico.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adrianiglesia.examentecnico.model.Hotel
import com.adrianiglesia.examentecnico.service.MainRepository

class MainActivityViewModel: ViewModel() {

    private var hotelList: MutableLiveData<List<Hotel>>? = MutableLiveData()
    private var message: MutableLiveData<String> = MutableLiveData()
    private val repository= MainRepository()

    fun getAllHotels(): LiveData<List<Hotel>>? {
        loadHotels()
        return hotelList
    }

    fun getMessage():LiveData<String>{
        return message
    }

    private fun loadHotels(){
        repository.getAllHotels({
            hotelList?.value = it
        },{
            message.value = it
            hotelList?.value = null

        })
    }
}