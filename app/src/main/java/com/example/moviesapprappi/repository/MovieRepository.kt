
package com.example.moviesapprappi.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.moviesapprappi.api.service.MovieService
import com.example.moviesapprappi.db.dao.MovieDao
import com.skydoves.sandwich.suspendOnSuccess

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository constructor(
  private val movieService: MovieService,
  private val movieDao: MovieDao
) : Repository {

  init {
    Log.d(MovieRepository::class.java.canonicalName, "Injection MovieRepository")
  }

  @WorkerThread
  fun loadKeywordList(id: Int) = flow {
    val movie = movieDao.getMovie(id)
    var keywords = movie.keywords
    if (keywords.isNullOrEmpty()) {
      val response = movieService.fetchKeywords(id)
      response.suspendOnSuccess {
        keywords = data.keywords
        movie.keywords = keywords
        emit(keywords)
        movieDao.updateMovie(movie)
      }
    } else {
      emit(keywords)
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadVideoList(id: Int) = flow {
    val movie = movieDao.getMovie(id)
    var videos = movie.videos
    if (videos.isNullOrEmpty()) {
      movieService.fetchVideos(id)
        .suspendOnSuccess {
          videos = data.results
          movie.videos = videos
          movieDao.updateMovie(movie)
          emit(videos)
        }
    } else {
      emit(videos)
    }
  }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun loadReviewsList(id: Int) = flow {
    val movie = movieDao.getMovie(id)
    var reviews = movie.reviews
    if (reviews.isNullOrEmpty()) {
      movieService.fetchReviews(id)
        .suspendOnSuccess {
          reviews = data.results
          movie.reviews = reviews
          movieDao.updateMovie(movie)
          emit(reviews)
        }
    } else {
      emit(reviews)
    }
  }.flowOn(Dispatchers.IO)
}
