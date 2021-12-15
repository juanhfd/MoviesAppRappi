package com.example.moviesapprappi.ui.tv

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.example.moviesapprappi.model.Keyword
import com.example.moviesapprappi.model.Review
import com.example.moviesapprappi.model.Video
import com.example.moviesapprappi.repository.TvRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val tvRepository: TvRepository
) : BindingViewModel() {

    private val tvIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

    private val keywordListFlow = tvIdSharedFlow.flatMapLatest {
        tvRepository.loadKeywordList(it)
    }

    @get:Bindable
    val keywordList: List<Keyword>? by keywordListFlow.asBindingProperty(viewModelScope, null)

    private val videoListFlow = tvIdSharedFlow.flatMapLatest {
        tvRepository.loadVideoList(it)
    }

    @get:Bindable
    val videoList: List<Video>? by videoListFlow.asBindingProperty(viewModelScope, null)

    private val reviewListFlow = tvIdSharedFlow.flatMapLatest {
        tvRepository.loadReviewsList(it)
    }

    @get:Bindable
    val reviewList: List<Review>? by reviewListFlow.asBindingProperty(viewModelScope, null)

    init {
        Log.d("TvDetailViewModel", "Injection TvDetailViewModel")
    }

    fun postTvId(id: Int) = tvIdSharedFlow.tryEmit(id)
}