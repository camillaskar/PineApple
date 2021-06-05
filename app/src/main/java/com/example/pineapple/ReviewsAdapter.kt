package com.example.pineapple

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewsAdapter(private val reviewList: List<Review>) :
    RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewsAdapter.ReviewViewHolder {
        val reviewView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.review_row, parent, false)
        return ReviewViewHolder(reviewView)

    }


    class ReviewViewHolder(reviewRowItemView: View) : RecyclerView.ViewHolder(reviewRowItemView) {
        val author: TextView
        val description: TextView
        val star: RatingBar

        init {
            author = reviewRowItemView.findViewById(R.id.user_name)
            description = reviewRowItemView.findViewById(R.id.desc)
            star = reviewRowItemView.findViewById(R.id.listitemrating)

        }

    }

    override fun onBindViewHolder(holder: ReviewsAdapter.ReviewViewHolder, position: Int) {
        val reviewItem: Review = reviewList[position]
        holder.author.text = reviewItem.author
        holder.description.text = reviewItem.description
        holder.star.rating = reviewItem.star

    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

}