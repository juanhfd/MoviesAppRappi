package com.example.moviesapprappi.ui.movie

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.moviesapprappi.R
import com.example.moviesapprappi.databinding.ActivityMovieDetailBinding
import com.example.moviesapprappi.model.Movie
import com.example.moviesapprappi.ui.tv.ReviewListAdapter
import com.example.moviesapprappi.ui.tv.VideoListAdapter
import com.example.moviesapprappi.viewmodel.MovieDetailViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity :
    BindingActivity<ActivityMovieDetailBinding>(R.layout.activity_movie_detail) {

    private val vm: MovieDetailViewModel by viewModels()
    private val intentMovie: Movie by bundleNonNull(MOVIE_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            activity = this@MovieDetailActivity
            viewModel = vm.apply { getMovieListFromId(intentMovie.id) }
            movie = intentMovie
            videoListAdapter = VideoListAdapter()
            reviewListAdapter = ReviewListAdapter()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return false
    }

    companion object {
        private const val MOVIE_ID = "movie"
        fun startActivityModel(context: Context?, movie: Movie?) {
            context?.intentOf<MovieDetailActivity> {
                putExtra(MOVIE_ID to movie)
                startActivity(context)
            }
        }
    }
}