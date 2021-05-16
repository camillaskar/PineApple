package com.example.pineapple.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = arrayOf(RestaurantEntity::class), version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDAO
}