package com.example.marvel.domain.repository

import com.example.marvel.domain.model.Character
import com.example.marvel.domain.model.DataContainer
import io.reactivex.Observable

interface ListCharactersRepository {
    fun getListCharacters(offset: Int): Observable<DataContainer<Character>>
}