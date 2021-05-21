package com.example.pineapple

interface ResponseCallback {
    fun postResults(restaurants: List<Restaurant>)
}