package com.example.marvel.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.example.marvel.data.local.AppDataBase
import com.example.marvel.data.remote.ApiService
import com.example.marvel.data.repository.paging.CharacterRemoteMediator
import com.example.marvel.data.repository.paging.CharactersPagingSource
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.repository.ListCharactersRepository
import io.reactivex.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ListCharactersRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val dataBase: AppDataBase
) : ListCharactersRepository {

    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    override fun getListCharacters(): Observable<PagingData<Character>> {
        return Pager(
            PagingConfig(pageSize = 20, enablePlaceholders = false, prefetchDistance = 6),
            remoteMediator = CharacterRemoteMediator(dataBase, service),
            pagingSourceFactory = { CharactersPagingSource(service) }
        ).observable
    }
}