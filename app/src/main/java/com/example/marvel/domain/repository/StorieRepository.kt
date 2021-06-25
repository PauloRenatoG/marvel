package com.example.marvel.domain.repository

import com.example.marvel.domain.model.DataContainer
import com.example.marvel.domain.model.Storie
import io.reactivex.Observable

interface StorieRepository {
    fun getListStorie(characterId: Int): Observable<DataContainer<Storie>>
}