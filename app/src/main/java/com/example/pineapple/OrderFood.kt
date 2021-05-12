package com.example.pineapple

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_order.view.*


class OrderFood:Fragment() {
    private val menuList by lazy {
        JsonReader.getMenus(context)
    }
    private var menuAdapter:MenuAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var relativeLayout = inflater.inflate(R.layout.fragment_order, container, false)
        menuAdapter = MenuAdapter(requireContext(),menuList )
        relativeLayout.gridMenu.layoutManager = GridLayoutManager(context, 2)
//        relativeLayout.gridMenu.adapter = menuAdapter


        return relativeLayout
    }




}