package com.example.moviesapprappi.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.moviesapprappi.model.Movie
import com.example.moviesapprappi.model.Tv
import com.example.moviesapprappi.repository.FindRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject
import androidx.databinding.Bindable

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val discoverRepository: FindRepository
) : BindingViewModel() {

    @get:Bindable
    var isMovieListLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var isTvListLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var isPeopleListLoading: Boolean by bindingProperty(false)
        private set

    private val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
    private val movieListFlow = moviePageStateFlow.flatMapLatest {
        isMovieListLoading = true
        discoverRepository.loadMovies(it) {
            isMovieListLoading = false
        }
    }

    @get:Bindable
    val movieList: List<Movie> by movieListFlow.asBindingProperty(viewModelScope, emptyList())

    private val tvPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
    private val tvListFlow = tvPageStateFlow.flatMapLatest {
        isTvListLoading = true
        discoverRepository.loadTvs(it) {
            isTvListLoading = false
        }
    }

    @get:Bindable
    val tvList: List<Tv> by tvListFlow.asBindingProperty(viewModelScope, emptyList())


    init {
        Log.d("MainActivityViewModel", "injection MainActivityViewModel")
    }

    fun postMoviePage(page: Int) = moviePageStateFlow.tryEmit(page)

    fun postTvPage(page: Int) = tvPageStateFlow.tryEmit(page)

}
