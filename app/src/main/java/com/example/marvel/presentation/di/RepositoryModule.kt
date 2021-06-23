package com.example.marvel.presentation.di

import com.example.marvel.data.repository.ListCharactersRepositoryImpl
import com.example.marvel.domain.repository.ListCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindListCharactersRepository(repository: ListCharactersRepositoryImpl): ListCharactersRepository
}