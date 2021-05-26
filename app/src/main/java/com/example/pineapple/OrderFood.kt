package com.example.pineapple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_order.view.*


class OrderFood  : Fragment(), MenuCallback {
    private val menuList by lazy {
        JsonReader.getMenus(context)
    }
    private var menuAdapter: MenuAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var relativeLayout = inflater.inflate(R.layout.fragment_order, container, false)
        menuAdapter = MenuAdapter(menuList, this)
        relativeLayout.gridMenu.layoutManager = GridLayoutManager(context, 2)
        relativeLayout.gridMenu.adapter = menuAdapter
        var totalNamesVIew = relativeLayout.foodNames
        var totalPriceView = relativeLayout.total
//        totalPriceView.text = totalPriceOfItem(total)


        var listMenu: List<Foods> = JsonReader.getMenus(context)


        return relativeLayout
    }

    var totalPrice = 0.0

    override fun totalPriceOfItem(total: Double) {
        totalPrice += total
        println("Kamilla's total of the item :  " + total + "  the whole total  " + totalPrice)
        view?.total?.text = totalPrice.toString()
    }


}
