package com.example.marvel.data.repository

import com.example.marvel.data.remote.ApiService
import com.example.marvel.domain.model.DataContainer
import com.example.marvel.domain.model.Serie
import com.example.marvel.domain.repository.SerieRepository
import io.reactivex.Observable
import javax.inject.Inject

class SerieRepositoryImpl @Inject constructor(
    private val service: ApiService
) : SerieRepository {
    override fun getListSerie(characterId: Int): Observable<DataContainer<Serie>> {
        return service
            .getListSeries(characterId = characterId)
            .map { it.data }
    }
}