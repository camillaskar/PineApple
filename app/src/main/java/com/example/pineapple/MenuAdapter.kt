package com.example.pineapple
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
// TODO change to be recyclerviewAdapter - check examples CustomAdapter.kt and ReviewAdapter.kt
class MenuAdapter(var context: Context,
        var menuList: List<Foods>) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var food_img: ImageView
    private lateinit var food_name: TextView
    lateinit var food_ingr: TextView
    lateinit var add_b:Button
    lateinit var priceBut: TextView
    lateinit var picker: NumberPicker
    override fun getCount(): Int {
        return menuList.size
    }
    override fun getItem(position: Int): Any? {
        return menuList[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView( position: Int, convertView: View?, parent: ViewGroup): View? {
        var view:View = View.inflate(context, R.layout.menu_row, null)
        food_img = view.findViewById(R.id.restaurant_img4)
        food_name = view.findViewById(R.id.name_res4)
        food_ingr = view.findViewById(R.id.categories4)
        add_b = view.findViewById(R.id.book_button4)
        picker = view.findViewById(R.id.picker)
        priceBut = view.findViewById(R.id.price)

        var menuListItem:Foods = menuList[position]

        food_name.text = menuListItem.name
        food_ingr.text = menuListItem.ingredients
        priceBut.text = menuListItem.price.toString()


        return view
    }
}