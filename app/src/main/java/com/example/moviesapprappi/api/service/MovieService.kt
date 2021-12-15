
package com.example.moviesapprappi.api.service

import com.example.moviesapprappi.model.network.KeywordListResponse
import com.example.moviesapprappi.model.network.ReviewMoviewTvListResponse
import com.example.moviesapprappi.model.network.VideoListResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

  @GET("/3/movie/{movie_id}/keywords")
  suspend fun fetchKeywords(@Path("movie_id") id: Int): ApiResponse<KeywordListResponse>

  @GET("/3/movie/{movie_id}/videos")
  suspend fun fetchVideos(@Path("movie_id") id: Int): ApiResponse<VideoListResponse>

  @GET("/3/movie/{movie_id}/reviews")
  suspend fun fetchReviews(@Path("movie_id") id: Int): ApiResponse<ReviewMoviewTvListResponse>
}
