package com.adrianiglesia.examentecnico.model


import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("address")
    val address: String,
    @SerializedName("amenities")
    val amenities: List<Amenity>,
    @SerializedName("city")
    val city: City,
    @SerializedName("description")
    val description: String,
    @SerializedName("geo_location")
    val geoLocation: GeoLocation,
    @SerializedName("id")
    val id: String,
    @SerializedName("main_picture")
    val mainPicture: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("reviews")
    val reviews: List<Review>,
    @SerializedName("stars")
    val stars: Int,
    @SerializedName("price")
    val price: Price
)