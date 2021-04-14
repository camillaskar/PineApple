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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Feed.newInstance] factory method to
 * create an instance of this fragment.
 */
class Feed : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_feed, container, false)
        var aral = arrayOf("Kamilla", "Aida", "Aliya",
                "Miras", "Sveta", "Askar", "Aral", "Kazakhstan", "Almaty", "Kyzylorda")
        val adapter = CustomAdapter()
        view.recycleRes.adapter = adapter
        view.recycleRes.layoutManager = LinearLayoutManager(context)
        val str = activity?.assets?.open("restaurant_response.json")?.bufferedReader().use { it?.readText() }
        val assetmanager = activity?.assets
        val restaurant_response = Gson().fromJson(str, RestaurantResponse::class.java)
        adapter.submit(restaurant_response.stores)
        println(" KKKAAAA " + str)
        Log.d("Restaurant response", "Restaurants " + restaurant_response.num_results)
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Feed.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Feed().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}