package com.example.marvel.data.repository

import com.example.marvel.data.remote.ApiService
import com.example.marvel.domain.model.Comic
import com.example.marvel.domain.model.DataContainer
import com.example.marvel.domain.repository.ComicRepository
import io.reactivex.Observable
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val service: ApiService
) : ComicRepository {
    override fun getListComic(characterId: Int): Observable<DataContainer<Comic>> {
        return service
            .getListComics(characterId = characterId)
            .map { it.data }
    }
}