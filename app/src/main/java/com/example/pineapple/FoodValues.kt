package com.example.pineapple

data class FoodValues (
    val menus: List<Foods>
    )

data class Foods(
        val name: String,
        val price: Double,
        val ingredients: String,
        var number: Int

)