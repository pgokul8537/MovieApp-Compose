package com.example.movieapp.di.repository

import com.example.movieapp.repository.DiscoverRepository
import com.example.movieapp.repository.DiscoverRepositoryImpl
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.repository.MovieRepositoryImpl
import com.example.movieapp.repository.SearchRepository
import com.example.movieapp.repository.SearchRepositoryImpl
import com.example.movieapp.repository.TvRepository
import com.example.movieapp.repository.TvRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideMovieRepository(implements: MovieRepositoryImpl): MovieRepository

    @Binds
    fun provideSearchRepository(implements: SearchRepositoryImpl): SearchRepository

    @Binds
    fun provideTvRepository(implements: TvRepositoryImpl): TvRepository

    @Binds
    fun provideDiscoverRepository(implements: DiscoverRepositoryImpl): DiscoverRepository

}