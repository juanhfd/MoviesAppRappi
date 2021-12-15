package com.example.moviesapprappi.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.moviesapprappi.api.service.TvService
import com.example.moviesapprappi.db.dao.TvDao
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvRepository constructor(
    private val tvService: TvService,
    private val tvDao: TvDao
) : Repository {

    init {
        Log.d("TvRepository", "Injection TvRepository")
    }

    @WorkerThread
    fun loadKeywordList(id: Int) = flow {
        val tv = tvDao.getTv(id) ?: return@flow
        var keywords = tv.keywords
        if (keywords.isNullOrEmpty()) {
            val response = tvService.fetchKeywords(id)
            response.suspendOnSuccess {
                keywords = data.keywords
                tv.keywords = keywords
                tvDao.updateTv(tv)
                emit(keywords)
            }
        } else {
            emit(keywords)
        }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun loadVideoList(id: Int) = flow {
        val tv = tvDao.getTv(id) ?: return@flow
        var videos = tv.videos
        if (videos.isNullOrEmpty()) {
            val response = tvService.fetchVideos(id)
            response.suspendOnSuccess {
                videos = data.results
                tv.videos = videos
                tvDao.updateTv(tv)
                emit(videos)
            }
        } else {
            emit(videos)
        }
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun loadReviewsList(id: Int) = flow {
        val tv = tvDao.getTv(id) ?: return@flow
        var reviews = tv.reviews
        if (reviews.isNullOrEmpty()) {
            val response = tvService.fetchReviews(id)
            response.suspendOnSuccess {
                reviews = data.results
                tv.reviews = reviews
                tvDao.updateTv(tv)
                emit(reviews)
            }
        } else {
            emit(reviews)
        }
    }.flowOn(Dispatchers.IO)
}
