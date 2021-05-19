package com.example.pineapple.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pineapple.database.RestaurantEntity.Companion.TableName

@Entity(tableName = TableName)
data class RestaurantEntity(
    val name: String,
    val description: String,
    val coverImgUrl: String,
    @PrimaryKey val id: Int
) {
    companion object {
        const val TableName = "Restaurants"
    }
}
