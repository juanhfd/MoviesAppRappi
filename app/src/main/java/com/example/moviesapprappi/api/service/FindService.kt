package com.example.moviesapprappi.api.service

import com.example.moviesapprappi.model.network.FindMovieResponse
import com.example.moviesapprappi.model.network.DiscoverTvResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FindService {

  @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
  suspend fun fetchFindMovie(@Query("page") page: Int): ApiResponse<FindMovieResponse>


  @GET("/3/discover/tv?language=en&sort_by=popularity.desc")
  suspend fun fetchFindTv(@Query("page") page: Int): ApiResponse<DiscoverTvResponse>
}
