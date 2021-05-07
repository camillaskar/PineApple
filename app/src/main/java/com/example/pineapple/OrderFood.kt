package com.example.pineapple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_feed.view.*

class OrderFood: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_order, container, false)
        val adapter = CustomAdapter(activity as MainActivity, childFragmentManager)
        view.recycleRes.adapter = adapter
        view.recycleRes.layoutManager = LinearLayoutManager(context)
        adapter.submit(JsonReader.getRestaurants(activity as MainActivity))
        return view
    }


}