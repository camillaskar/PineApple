package com.example.pineapple

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_feed.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment(), ResponseCallback {

    private val adapter by lazy {
        FeedAdapter(activity as MainActivity, childFragmentManager)
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
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.transition)


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


      postponeEnterTransition()
      view.recycleRes.doOnPreDraw {
         startPostponedEnterTransition()
        }
    }

    fun hideKeyboard() {
        val hh: Long
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun postResults(restaurants: List<Restaurant>) {
        adapter.submit(restaurants)
    }
}

