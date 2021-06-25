package com.example.marvel.domain.repository

import com.example.marvel.domain.model.DataContainer
import com.example.marvel.domain.model.Serie
import io.reactivex.Observable

interface SerieRepository {
    fun getListSerie(characterId: Int): Observable<DataContainer<Serie>>
}