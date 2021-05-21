package com.example.pineapple

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_feed.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [Feed.newInstance] factory method to
 * create an instance of this fragment.
 */
class Feed : Fragment(), ResponseCallback {

    private val adapter by lazy {
        CustomAdapter(activity as MainActivity, childFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_feed, container, false)

        view.recycleRes.adapter = adapter
        view.recycleRes.layoutManager = LinearLayoutManager(context)
        JsonReader.getRestaurants(activity as MainActivity, this)

        view.search_button.setOnClickListener {
            val result = JsonReader.search(view.input.text.toString())
            adapter.submit(result)
            hideKeyboard()
        }

        return view
    }

    fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun postResults(restaurants: List<Restaurant>) {
        adapter.submit(restaurants)
    }
}

