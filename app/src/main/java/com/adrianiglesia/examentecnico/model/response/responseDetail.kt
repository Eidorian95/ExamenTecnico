package com.adrianiglesia.examentecnico.model.response


import com.google.gson.annotations.SerializedName

data class responseDetail(
    @SerializedName("hotel")
    val hotel: Hotel,
    @SerializedName("id")
    val id: String,
    @SerializedName("price")
    val price: Price
) {
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
        val stars: Int
    ) {
        data class Amenity(
            @SerializedName("description")
            val description: String,
            @SerializedName("id")
            val id: String
        )

        data class City(
            @SerializedName("administrative_division")
            val administrativeDivision: AdministrativeDivision,
            @SerializedName("code")
            val code: String,
            @SerializedName("country")
            val country: Country,
            @SerializedName("id")
            val id: String,
            @SerializedName("name")
            val name: String
        ) {
            data class AdministrativeDivision(
                @SerializedName("code")
                val code: String,
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String
            )

            data class Country(
                @SerializedName("code")
                val code: String,
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String
            )
        }

        data class GeoLocation(
            @SerializedName("latitude")
            val latitude: Double,
            @SerializedName("longitude")
            val longitude: Double
        )

        data class Review(
            @SerializedName("comments")
            val comments: Comments,
            @SerializedName("user")
            val user: User
        ) {
            data class Comments(
                @SerializedName("good")
                val good: String,
                @SerializedName("type")
                val type: String
            )

            data class User(
                @SerializedName("country")
                val country: String,
                @SerializedName("first_name")
                val firstName: String,
                @SerializedName("last_name")
                val lastName: String,
                @SerializedName("name")
                val name: String
            )
        }
    }

    data class Price(
        @SerializedName("base")
        val base: Int,
        @SerializedName("best")
        val best: Int,
        @SerializedName("currency")
        val currency: Currency,
        @SerializedName("final_price")
        val finalPrice: Boolean
    ) {
        data class Currency(
            @SerializedName("code")
            val code: String,
            @SerializedName("mask")
            val mask: String,
            @SerializedName("ratio")
            val ratio: Double
        )
    }
}