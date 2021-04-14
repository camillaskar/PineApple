package com.example.pineapple

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter() :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var list = arrayListOf<Restaurant>()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val descriptionView: TextView
        val bookButton: Button

        init {
            // Define click listener for the ViewHolder's View.
            nameView = view.findViewById(R.id.name_res)
            descriptionView = view.findViewById(R.id.categories)
            bookButton = view.findViewById(R.id.book_button)

        }
    }

    fun submit(dataSet: List<Restaurant>){
        list.addAll(dataSet)
    }



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.restaurant_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val restaurant:Restaurant = list[position]
        viewHolder.nameView.text = restaurant.name
        viewHolder.descriptionView.text = restaurant.description
        viewHolder.bookButton.setOnClickListener {
            Log.d("Button book", "is okay")
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = list.size



}
