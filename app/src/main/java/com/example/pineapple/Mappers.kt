package com.example.pineapple

import com.example.pineapple.database.RestaurantEntity

fun Restaurant.toRestaurantEntity(): RestaurantEntity {
    return RestaurantEntity(
        name = this.name,
        description = this.description,
        coverImgUrl = this.cover_img_url,
        id = this.id
    )
}

fun RestaurantEntity.toRestaurant(): Restaurant {
    return Restaurant(
        name = this.name,
        description = this.description,
        cover_img_url = this.coverImgUrl,
        id = this.id
    )
}