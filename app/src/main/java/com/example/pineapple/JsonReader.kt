package com.example.pineapple

import android.content.Context
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

    fun getReviews(restaurantId:Int, context: Context?): List<Review>{
        val reviewString = context?.assets?.open("reviews.json")?.bufferedReader().use{it?.readText()}
        val reviewResponses = Gson().fromJson(reviewString, ReviewResponse::class.java)
        return reviewResponses.reviews.filter { it.restaurant_id == restaurantId }
    }

}