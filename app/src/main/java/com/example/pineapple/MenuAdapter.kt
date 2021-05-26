package com.example.pineapple

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_layout.view.*

class MenuAdapter(val menuList: List<Foods>,
val menuCallback: MenuCallback
) : RecyclerView.Adapter<MenuAdapter.MenuViewholder>() {


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
        /**
         * menuItem = coca cola
         *
         */
        val menuItem: Foods = menuList[position]
        println("Kamilla's menuItem :  " + menuItem)
        holder.name.text = menuItem.name // name.text = "coca cola"
        holder.ingredients.text = menuItem.ingredients // ingredients.text = "chemicals and sugar"
        holder.price.text = menuItem.price.toString() // price.text = "5.0"
        holder.number.value = menuItem.number // number.value = 0 // number.value = 2

        holder.addButton.setOnClickListener {
            val total = menuItem.price * holder.number.value
            menuCallback.totalPriceOfItem(total)
            println("Kamilla's :  " + total)

        }

    }

    override fun getItemCount(): Int {
        return menuList.size
    }




}
