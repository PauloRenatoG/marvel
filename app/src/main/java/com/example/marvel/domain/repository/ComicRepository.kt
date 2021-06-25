package com.example.marvel.domain.repository

import com.example.marvel.domain.model.Comic
import com.example.marvel.domain.model.DataContainer
import io.reactivex.Observable

interface ComicRepository {
    fun getListComic(characterId: Int): Observable<DataContainer<Comic>>
}