package com.adrianiglesia.examentecnico.service

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object MyRetrofit {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/"

    fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }
        return retrofit!!
    }
}
