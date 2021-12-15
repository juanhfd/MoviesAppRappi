package com.example.moviesapprappi.model.network

import com.example.moviesapprappi.model.NetworkResponseModel
import com.example.moviesapprappi.model.Review

class ReviewMoviewTvListResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val total_pages: Int,
    val total_results: Int
) : NetworkResponseModel