package com.example.pineapple

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_layout.view.*

// TODO change to be recyclerviewAdapter - check examples CustomAdapter.kt and ReviewAdapter.kt
class MenuAdapter(var menuList: List<Foods>) : RecyclerView.Adapter<MenuAdapter.MenuViewholder>() {
//    private var layoutInflater: LayoutInflater? = null
//    private lateinit var food_img: ImageView
//    private lateinit var food_name: TextView
//    lateinit var food_ingr: TextView
//    lateinit var add_b:Button
//    lateinit var priceBut: TextView
//    lateinit var picker: NumberPicker

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuViewholder {
        val menuView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_row, parent, false)
        return MenuViewholder(menuView)

    }


    class MenuViewholder(menuRowItemView: View) : RecyclerView.ViewHolder(menuRowItemView) {
        val name: TextView
        val price: TextView
        val ingredients: TextView
        var number: NumberPicker
        val addButton: Button
        val foodImg: ImageView
//        var totalPrice:TextView
//        var totalNames: TextView

        init {
            name = menuRowItemView.findViewById(R.id.name_res4)
            ingredients = menuRowItemView.findViewById(R.id.categories4)
            price = menuRowItemView.findViewById(R.id.price)
            number = menuRowItemView.findViewById(R.id.picker)
            addButton = menuRowItemView.findViewById(R.id.book_button4)
            foodImg = menuRowItemView.findViewById(R.id.restaurant_img4)

            number.minValue = 0
            number.maxValue = 6
        }


    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewholder, position: Int) {
        val menuItem: Foods = menuList[position]
        holder.name.text = menuItem.name
        holder.ingredients.text = menuItem.ingredients
        holder.price.text = menuItem.price.toString()
        holder.number.value = menuItem.number

        holder.addButton.setOnClickListener {
            var total = JsonReader.getTotalPrice(menuList, menuItem.price, holder.number.value)
            println("Kamilla's :  " + total)

        }

    }

    override fun getItemCount(): Int {
        return menuList.size
    }


}

private fun TextView.addTextChangedListener(name: TextView) {

}

//    override fun getCount(): Int {
//        return menuList.size
//    }
//    override fun getItem(position: Int): Any? {
//        return menuList[position]
//    }
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//    override fun getView( position: Int, convertView: View?, parent: ViewGroup): View? {
//        var view:View = View.inflate(context, R.layout.menu_row, null)
//        food_img = view.findViewById(R.id.restaurant_img4)
//        food_name = view.findViewById(R.id.name_res4)
//        food_ingr = view.findViewById(R.id.categories4)
//        add_b = view.findViewById(R.id.book_button4)
//        picker = view.findViewById(R.id.picker)
//        priceBut = view.findViewById(R.id.price)
//
//        var menuListItem:Foods = menuList[position]
//
//        food_name.text = menuListItem.name
//        food_ingr.text = menuListItem.ingredients
//        priceBut.text = menuListItem.price.toString()
//
//
//        return view
//    }
//}