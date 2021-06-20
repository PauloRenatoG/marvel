package com.example.marvel.domain.repository

import com.example.marvel.domain.model.CharacterDataContainer
import io.reactivex.Observable

interface ListCharactersRepository {
    fun getListCharacters(): Observable<CharacterDataContainer>
}