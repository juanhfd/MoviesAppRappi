package com.example.moviesapprappi.model.network

import com.example.moviesapprappi.model.NetworkResponseModel
import com.example.moviesapprappi.model.Video

data class VideoListResponse(
    val id: Int,
    val results: List<Video>
) : NetworkResponseModel