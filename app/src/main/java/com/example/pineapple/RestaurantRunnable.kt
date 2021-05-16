package com.example.pineapple

class RestaurantRunnable(private val block:() -> Unit): Runnable {
    override fun run() {
        block
    }
}