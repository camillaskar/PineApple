package com.example.pineapple

data class RestaurantResponse (
   val num_results:Int,
   val is_first_time_user:Boolean,
   val sort_order:String,
   val stores:List<Restaurant>
        )
data class Restaurant(
    val name:String,
    val description:String,
    val cover_img_url:String
)