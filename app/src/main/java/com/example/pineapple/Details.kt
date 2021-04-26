package com.example.pineapple

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.fragment_feed.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [Details.newInstance] factory method to
 * create an instance of this fragment.
 */
class Details : Fragment() {
    // TODO: Rename and change types of parameters
    private var restaurantID: Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restaurantID = it.getInt(ID)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var reviews = inflater.inflate(R.layout.fragment_details, container, false)
        view?.recycleRes?.layoutManager = LinearLayoutManager(context)
        var detailss = JsonReader.getRestaurantDetails(restaurantID!!)
        reviews.nameRest.text = detailss?.name
        return reviews
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(restaurantId:Int) =
            Details().apply {
                arguments = Bundle().apply {
                    putInt(ID, restaurantId)
                }
            }
    }
}