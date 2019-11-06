package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("base")
    val base: Int,
    @SerializedName("best")
    val best: Int,
    @SerializedName("currency")
    val currency: Currency,
    @SerializedName("final_price")
    val finalPrice: Boolean
)