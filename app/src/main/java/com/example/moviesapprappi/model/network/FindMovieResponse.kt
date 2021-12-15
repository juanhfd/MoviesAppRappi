package com.example.moviesapprappi.model.network

import com.example.moviesapprappi.model.Movie
import com.example.moviesapprappi.model.NetworkResponseModel

data class FindMovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel