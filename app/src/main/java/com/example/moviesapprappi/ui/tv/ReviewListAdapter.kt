package com.example.moviesapprappi.ui.tv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapprappi.R
import com.example.moviesapprappi.databinding.ItemReviewBinding
import com.example.moviesapprappi.model.Review
import com.skydoves.bindables.binding

class ReviewListAdapter : RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder>() {

    private val items: MutableList<Review> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListViewHolder {
        val binding = parent.binding<ItemReviewBinding>(R.layout.item_review)
        return ReviewListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewListViewHolder, position: Int) {
        with(holder.binding) {
            review = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addReviewList(reviews: List<Review>) {
        items.addAll(reviews)
        notifyItemRangeInserted(items.size + 1, reviews.size)
    }

    class ReviewListViewHolder(val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
