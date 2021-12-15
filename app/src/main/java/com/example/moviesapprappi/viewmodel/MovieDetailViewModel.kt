package com.example.moviesapprappi.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.moviesapprappi.model.Keyword
import com.example.moviesapprappi.model.Review
import com.example.moviesapprappi.model.Video
import com.example.moviesapprappi.repository.MovieRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BindingViewModel() {

    private val movieIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

    private val keywordListFlow = movieIdSharedFlow.flatMapLatest {
        movieRepository.loadKeywordList(it)
    }

    @get:Bindable
    val keywordList: List<Keyword>? by keywordListFlow.asBindingProperty(viewModelScope, null)

    private val videoListFlow = movieIdSharedFlow.flatMapLatest {
        movieRepository.loadVideoList(it)
    }

    @get:Bindable
    val videoList: List<Video>? by videoListFlow.asBindingProperty(viewModelScope, null)

    private val reviewListFlow = movieIdSharedFlow.flatMapLatest {
        movieRepository.loadReviewsList(it)
    }

    @get:Bindable
    val reviewList: List<Review>? by reviewListFlow.asBindingProperty(viewModelScope, null)

    init {
        Log.d("MovieDetailViewModel", "Injection MovieDetailViewModel")
    }

    @MainThread
    fun getMovieListFromId(id: Int) = movieIdSharedFlow.tryEmit(id)
}