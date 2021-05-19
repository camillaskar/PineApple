package com.example.pineapple

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_feed.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [Feed.newInstance] factory method to
 * create an instance of this fragment.
 */
class Feed : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_feed, container, false)
        val adapter = CustomAdapter(activity as MainActivity, childFragmentManager)
        view.recycleRes.adapter = adapter
        view.recycleRes.layoutManager = LinearLayoutManager(context)
        adapter.submit(JsonReader.getRestaurants(activity as MainActivity))

        view.search_button.setOnClickListener {
          val result =  JsonReader.search(view.input.text.toString())
            adapter.submit(result)
        }

        return view
    }


}