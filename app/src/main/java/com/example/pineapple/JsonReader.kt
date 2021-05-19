package com.example.pineapple

import android.content.Context
import androidx.room.Room
import com.example.pineapple.database.AppRoomDatabase
import com.google.gson.Gson
import java.util.concurrent.atomic.AtomicBoolean

object JsonReader {
    private var menus = listOf<Foods>()

    private var db: AppRoomDatabase? = null

    private fun getResDatabase(context: Context): AppRoomDatabase {
        if (db == null) {
            db = Room.databaseBuilder(context, AppRoomDatabase::class.java, "database-name").build()
        }
        return db!!
    }

    private val restaurantDao by lazy {
        db!!.restaurantDao()
    }

    fun getRestaurants(activity: MainActivity): List<Restaurant> {
        getResDatabase(activity)
        val isEmpty = AtomicBoolean(false)
        backgroudthread {
            isEmpty.set(restaurantDao.isEmpty())
        }
        var restaurants = emptyList<Restaurant>()
        if (isEmpty.get()) {
            val stringg = activity?.assets?.open("restaurant_response.json")?.bufferedReader()
                .use { it?.readText() }
            val restaurant_response = Gson().fromJson(stringg, RestaurantResponse::class.java)
            backgroudthread {
                restaurantDao.insertAll(
                    restaurant_response.stores.map { it.toRestaurantEntity() }
                )
            }

            restaurants = restaurant_response.stores
        } else {
            backgroudthread {
                restaurants = restaurantDao.getAll().map { it.toRestaurant() }
            }
        }
        return restaurants
    }

    fun getRestaurantDetails(id: Int): Restaurant? {

        var restaurant: Restaurant? = null
        backgroudthread {
            restaurant = restaurantDao.findByID(id).toRestaurant()
        }
        return restaurant

    }

    fun getReviews(restaurantId: Int, context: Context?): List<Review> {
        val reviewString =
            context?.assets?.open("reviews.json")?.bufferedReader().use { it?.readText() }
        val reviewResponses = Gson().fromJson(reviewString, ReviewResponse::class.java)
        return reviewResponses.reviews.filter { it.restaurant_id == restaurantId }
    }

    fun getMenus(context: Context?): List<Foods> {
        val menuString =
            context?.assets?.open("listFood.json")?.bufferedReader().use { it?.readText() }
        val menuResponse = Gson().fromJson(menuString, FoodValues::class.java)
        menus = menuResponse.menus
        return menus
    }

    fun getPriceOfItem(menuList: List<Foods>, initialPrice: Double, number: Int): Double {

        var totalOfItem = initialPrice * number

        return totalOfItem
    }

    fun getTotalPrice(menuList: List<Foods>, initialPrice: Double, number: Int): Double {
        var total = 0.0
        for (item in menuList) {
            var totalofItem = initialPrice * number
            total += totalofItem
        }
        return total
    }

    fun backgroudthread(block: () -> Unit) {
        val thread = Thread(Runnable() {
            block.invoke()
        }
        )
        thread.start()
        thread.join()
    }
}