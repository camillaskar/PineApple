package com.example.pineapple

data class RestaurantResponse(
    val num_results: Int,
    val is_first_time_user: Boolean,
    val sort_order: String,
    val stores: List<Restaurant>
)

data class Restaurant(
    val name: String,
    val description: String,
    val cover_img_url: String,
    val id: Int,
//    val menu:List<Foods>
)
//data class Foods(
//        val popularItems:List<Meals>
//)
//data class Meals(
//        val price: Int,
//        val name: String,
//        val description: String,
//        val img_url:String
//)