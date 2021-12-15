package com.example.moviesapprappi.model.network

import com.example.moviesapprappi.model.NetworkResponseModel
import com.example.moviesapprappi.model.Tv

data class DiscoverTvResponse(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
    val total_pages: Int
) : NetworkResponseModel