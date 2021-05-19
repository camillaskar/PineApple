package com.example.pineapple

import android.accounts.AuthenticatorDescription

data class ReviewResponse(
        val reviews: List<Review>,
        val restaurant_id: Int
)

data class Review(
        val restaurant_id: Int,
        val author: String,
        val description: String,
        val star: Float
)



