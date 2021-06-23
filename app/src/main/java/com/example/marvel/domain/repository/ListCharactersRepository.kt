package com.example.marvel.domain.repository

import androidx.paging.PagingData
import com.example.marvel.domain.model.Character
import io.reactivex.Observable

interface ListCharactersRepository {
    fun getListCharacters(): Observable<PagingData<Character>>
}