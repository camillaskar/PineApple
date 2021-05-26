package com.example.pineapple

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class FeedAdapter(
    private val navigation: Navigation,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    private var list = arrayListOf<Restaurant>()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val descriptionView: TextView
        val bookButton: Button
        val coverView: ImageView
        val orderButton: Button



        init {
            // Define click listener for the ViewHolder's View.
            nameView = view.findViewById(R.id.name_res)
            descriptionView = view.findViewById(R.id.categories)
            bookButton = view.findViewById(R.id.book_button)
            coverView = view.findViewById(R.id.restaurant_img)
            orderButton = view.findViewById(R.id.order_button)

        }
    }

    fun submit(dataSet: List<Restaurant>) {
        list.clear()
        list.addAll(dataSet)
        notifyDataSetChanged()
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.restaurant_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    //This is where the data is being passed to the ViewHolder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val restaurant: Restaurant = list[position]
        viewHolder.nameView.text = restaurant.name
        viewHolder.descriptionView.text = restaurant.description
        viewHolder.bookButton.setOnClickListener {
            Log.d("Button book", "is okay")
            var reserved = BookATableDialogFragment.openDialog(restaurant.name)
            reserved.show(fragmentManager, "Dialog")
        }
        viewHolder.orderButton.setOnClickListener {
            navigation.openFragment(fragment = OrderFood())
        }

        viewHolder.itemView.setOnClickListener {
            navigation.openFragment(fragment = Details.newInstance(restaurant.id))
        }



        Glide
            .with(viewHolder.itemView)
            .load(restaurant.cover_img_url)
            .placeholder(R.drawable.ic_android_black_24dp)
            .into(viewHolder.coverView);

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = list.size


}
