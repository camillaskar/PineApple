package com.example.pineapple

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {
    @GET("/v1/store_feed")
    fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lng") long: Double,
        @Query("offset")  offset: Int,
        @Query("limit")  limit: Int
    ): Call<RestaurantResponse>
}