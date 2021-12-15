package com.example.moviesapprappi.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapprappi.R
import com.example.moviesapprappi.extensions.visible
import com.example.moviesapprappi.model.*
import com.example.moviesapprappi.ui.main.MovieListAdapter
import com.example.moviesapprappi.ui.main.TvListAdapter
import com.example.moviesapprappi.ui.tv.ReviewListAdapter
import com.example.moviesapprappi.ui.tv.VideoListAdapter
import com.example.moviesapprappi.viewmodel.MainActivityViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
        view.adapter = baseAdapter
    }

    @JvmStatic
    @BindingAdapter("adapterMovieList")
    fun bindAdapterMovieList(view: RecyclerView, movies: List<Movie>?) {
        movies.whatIfNotNull {
            (view.adapter as? MovieListAdapter)?.addMovieList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("paginationMovieList")
    fun paginationMovieList(view: RecyclerView, viewModel: MainActivityViewModel) {
        RecyclerViewPaginator(
            recyclerView = view,
            isLoading = { viewModel.isMovieListLoading },
            loadMore = { viewModel.postMoviePage(it) },
            onLast = { false }
        ).run {
            threshold = 4
            currentPage = 1
        }
    }


    @JvmStatic
    @BindingAdapter("adapterTvList")
    fun bindAdapterTvList(view: RecyclerView, tvs: List<Tv>?) {
        tvs.whatIfNotNull { items ->
            view.adapter.whatIfNotNullAs<TvListAdapter> {
                it.addTvList(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("paginationTvList")
    fun paginationTvList(view: RecyclerView, viewModel: MainActivityViewModel) {
        RecyclerViewPaginator(
            recyclerView = view,
            isLoading = { viewModel.isTvListLoading },
            loadMore = { viewModel.postTvPage(it) },
            onLast = { false }
        ).run {
            threshold = 4
            currentPage = 1
        }
    }

    @JvmStatic
    @BindingAdapter("adapterVideoList")
    fun bindAdapterVideoList(view: RecyclerView, videos: List<Video>?) {
        videos.whatIfNotNullOrEmpty { items ->
            view.adapter.whatIfNotNullAs<VideoListAdapter> {
                it.addVideoList(items)
                view.visible()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterReviewList")
    fun bindAdapterReviewList(view: RecyclerView, reviews: List<Review>?) {
        view.setHasFixedSize(true)
        reviews.whatIfNotNullOrEmpty { items ->
            view.adapter.whatIfNotNullAs<ReviewListAdapter> {
                it.addReviewList(items)
                view.visible()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("mapKeywordList")
    fun bindMapKeywordList(chipGroup: ChipGroup, keywords: List<Keyword>?) {
        keywords.whatIfNotNullOrEmpty {
            chipGroup.visible()
            for (keyword in it) {
                chipGroup.addView(
                    Chip(chipGroup.context).apply {
                        text = keyword.name
                        isCheckable = false
                        setTextAppearanceResource(R.style.ChipTextStyle)
                        setChipBackgroundColorResource(R.color.colorPrimary)
                    }
                )
            }
        }
    }

}
