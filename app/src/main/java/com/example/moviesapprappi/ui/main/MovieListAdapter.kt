package com.example.moviesapprappi.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapprappi.R
import com.example.moviesapprappi.databinding.ItemPosterBinding
import com.example.moviesapprappi.model.Movie
import com.example.moviesapprappi.ui.movie.MovieDetailActivity
import com.skydoves.bindables.binding

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private val items: MutableList<Movie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = parent.binding<ItemPosterBinding>(R.layout.item_poster)
        return MovieListViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val movie = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                MovieDetailActivity.startActivityModel(it.context, items[movie])
            }
        }
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        with(holder.binding) {
            movie = items[position]
            executePendingBindings()
        }
    }

    fun addMovieList(movies: List<Movie>) {
        val previousItemSize = items.size
        items.addAll(movies)
        notifyItemRangeInserted(previousItemSize, movies.size)
    }

    override fun getItemCount(): Int = items.size

    class MovieListViewHolder(val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root)
}
