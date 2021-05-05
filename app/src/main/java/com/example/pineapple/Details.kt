package com.example.pineapple

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*
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
    private var menuImgg: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restaurantID = it.getInt(ID)
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var detailsView = inflater.inflate(R.layout.fragment_details, container, false)
      //  view?.recycleRes?.layoutManager = LinearLayoutManager(context)
        var detailss = JsonReader.getRestaurantDetails(restaurantID!!)
        detailsView.nameRest.text = detailss?.name
        detailsView.categoryRest.text = detailss?.description

        Glide
                .with(detailsView.menuImg)
                .load(detailss?.cover_img_url)
                .placeholder(R.drawable.ic_android_black_24dp)
                .into(detailsView.menuImg);
        var listRev: List<Review> = JsonReader.getReviews(restaurantID!!, context)
        val reviewAdapter = ReviewsAdapter(listRev)
        detailsView.recycleReview.adapter = reviewAdapter
        detailsView.recycleReview.layoutManager = LinearLayoutManager(context)

        if(listRev.isEmpty()){
            detailsView.recycleReview.visibility = View.GONE
            detailsView.noReview.visibility = View.VISIBLE
        } else{
            detailsView.noReview.visibility = View.GONE
            detailsView.recycleReview.visibility = View.VISIBLE
        }
        var stars:List<Float> = listRev.map { it.star  }
        println("This is the rating " + stars)

        var average:Float = stars.average().toFloat()
        println("This is the rating average " + average )
        detailsView.list_res2.text = getString(R.string.reviewRes, average)

        return detailsView
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