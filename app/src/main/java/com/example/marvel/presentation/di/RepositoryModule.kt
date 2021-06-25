package com.example.marvel.presentation.di

import com.example.marvel.data.repository.*
import com.example.marvel.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindListCharactersRepository(repository: ListCharactersRepositoryImpl): ListCharactersRepository

    @Binds
    fun bindComicRepository(repository: ComicRepositoryImpl): ComicRepository

    @Binds
    fun bindSerieRepository(repository: SerieRepositoryImpl): SerieRepository

    @Binds
    fun bindStorieRepository(repository: StorieRepositoryImpl): StorieRepository

    @Binds
    fun bindEventRepository(repository: EventRepositoryImpl): EventRepository
}