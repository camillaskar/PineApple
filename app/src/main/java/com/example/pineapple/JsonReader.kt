package com.example.pineapple

import android.content.Context
import android.widget.Toast
import androidx.room.Room
import com.example.pineapple.database.AppRoomDatabase
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.atomic.AtomicBoolean

object JsonReader {
    private var menus = listOf<Foods>()

    private var db: AppRoomDatabase? = null

    private val retrofit:Retrofit by lazy {
            Retrofit.Builder()
            .baseUrl("https://api.doordash.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val restaurantService: RestaurantService by lazy {
        retrofit.create(RestaurantService::class.java)
    }

    private fun getRestaurantsFromAPI(): Call<RestaurantResponse>{
        return restaurantService.getRestaurants(
            lat = 37.422740, long = -122.139956,
            offset = 0,
            limit = 100
        )
    }

    private fun getResDatabase(context: Context): AppRoomDatabase {
        if (db == null) {
            db = Room.databaseBuilder(context, AppRoomDatabase::class.java, "database-name").build()
        }
        return db!!
    }

    private val restaurantDao by lazy {
        db!!.restaurantDao()
    }

    fun getRestaurants(activity: MainActivity, responseCallback:ResponseCallback) {
        getResDatabase(activity)
        val isEmpty = AtomicBoolean(false)
        backgroudthread {
            isEmpty.set(restaurantDao.isEmpty())
        }
        if (isEmpty.get()) {

            val restaurantCall = getRestaurantsFromAPI()
            restaurantCall.enqueue(object: Callback<RestaurantResponse> {
                override fun onResponse(
                    call: Call<RestaurantResponse>,
                    response: Response<RestaurantResponse>

                ) {
                    println("API response:: " + response.body() )
                    backgroudthread {
                        response.body()?.stores?.map { it.toRestaurantEntity() }?.let {
                            restaurantDao.insertAll(
                                it
                            )
                        }
                    }
                    responseCallback.postResults(response.body()?.stores ?: emptyList())
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    println("API response error:: " + t )
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.error_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        } else {
            backgroudthread {
                responseCallback.postResults(restaurantDao.getAll().map { it.toRestaurant() })
            }
        }
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

    fun getPriceOfItem(initialPrice: Double, number: Int): Double {

        val totalOfItem = initialPrice * number

        return totalOfItem
    }

    fun getTotalPrice(menuList: List<Foods>, initialPrice: Double, number: Int): Double {
        var total = 0.0
        for (item in menuList) {
            val totalofItem = initialPrice * number
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

    fun search(searchString: String):List<Restaurant>{
        println("API response search:: " + searchString )
        var search:List<Restaurant> = emptyList<Restaurant>()
        backgroudthread {
            search = restaurantDao.search(searchString).map { it.toRestaurant() }
        }
        return search
    }

}