package com.example.marvel.data.repository

import com.example.marvel.data.remote.ApiService
import com.example.marvel.domain.model.DataContainer
import com.example.marvel.domain.model.Storie
import com.example.marvel.domain.repository.StorieRepository
import io.reactivex.Observable
import javax.inject.Inject

class StorieRepositoryImpl @Inject constructor(
    private val service: ApiService
) : StorieRepository {
    override fun getListStorie(characterId: Int): Observable<DataContainer<Storie>> {
        return service
            .getListStories(characterId = characterId)
            .map { it.data }
    }
}