package com.example.pineapple

import com.google.gson.Gson

object JsonReader {
    private var restaurants = listOf<Restaurant>()


    fun getRestaurants(activity: MainActivity): List<Restaurant>{
        val stringg = activity?.assets?.open("restaurant_response.json")?.bufferedReader().use { it?.readText() }
        val restaurant_response = Gson().fromJson(stringg, RestaurantResponse::class.java)
        restaurants = restaurant_response.stores
        return restaurants
    }

    fun getRestaurantDetails(id: Int): Restaurant?{
        return restaurants.firstOrNull { it.id == id}

    }

}