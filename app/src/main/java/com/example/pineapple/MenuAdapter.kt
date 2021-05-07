package com.example.pineapple
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

internal class MenuAdapter(
        menuList: List<Foods>
) :
        BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var food_img: ImageView
    private lateinit var food_name: TextView
    lateinit var food_ingr: TextView
    lateinit var add_b:Button
    lateinit var picker: NumberPicker
    override fun getCount(): Int {
        return numbersInWords.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView( position: Int, convertView: View?, parent: ViewGroup): View? {
        if (layoutInflater == null) {
            layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.menu_row, null)
        }
        food_img = convertView!!.findViewById(R.id.restaurant_img)
        food_name = convertView.findViewById(R.id.name_res)
        food_img.setImageResource(numberImage[position])
        food_name.text = numbersInWords[position]
        return convertView
    }
}