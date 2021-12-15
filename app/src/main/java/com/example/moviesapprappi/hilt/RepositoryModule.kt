package com.example.moviesapprappi.hilt


import com.example.moviesapprappi.api.service.MovieService
import com.example.moviesapprappi.api.service.FindService
import com.example.moviesapprappi.api.service.TvService
import com.example.moviesapprappi.db.dao.MovieDao
import com.example.moviesapprappi.db.dao.TvDao
import com.example.moviesapprappi.repository.FindRepository
import com.example.moviesapprappi.repository.MovieRepository
import com.example.moviesapprappi.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideDiscoverRepository(
        discoverService: FindService,
        movieDao: MovieDao,
        tvDao: TvDao
    ): FindRepository {
        return FindRepository(discoverService, movieDao, tvDao)
    }

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(
        movieService: MovieService,
        movieDao: MovieDao
    ): MovieRepository {
        return MovieRepository(movieService, movieDao)
    }

    @Provides
    @ViewModelScoped
    fun provideTvRepository(
        tvService: TvService,
        tvDao: TvDao
    ): TvRepository {
        return TvRepository(tvService, tvDao)
    }
}
