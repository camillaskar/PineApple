package com.example.pineapple.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pineapple.Restaurant

@Dao
interface RestaurantDAO {
    @Query("SELECT * FROM ${RestaurantEntity.TableName}")
    fun getAll(): List<RestaurantEntity>

    @Query("SELECT * FROM ${RestaurantEntity.TableName} WHERE id IN (:restaurantIds)")
    fun loadAllByIds(restaurantIds: IntArray): List<RestaurantEntity>

    @Query("SELECT * FROM ${RestaurantEntity.TableName} WHERE id = :resid")
    fun findByID(resid: Int): RestaurantEntity

    @Insert
    fun insertAll(restaurants: List<RestaurantEntity>)

    @Delete
    fun delete(restaurant: RestaurantEntity)

    @Query("SELECT COUNT (*) FROM ${RestaurantEntity.TableName}")
    fun count(): Int

    fun isEmpty() = count() == 0

    @Query("SELECT * FROM ${RestaurantEntity.TableName} WHERE name OR description LIKE '%' || :searchString || '%'")
    fun search (searchString: String): List<RestaurantEntity>
}