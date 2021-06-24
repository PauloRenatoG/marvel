package com.example.marvel.data.repository

import com.example.marvel.data.remote.ApiService
import com.example.marvel.domain.model.CharacterDataContainer
import com.example.marvel.domain.repository.ListCharactersRepository
import io.reactivex.Observable
import javax.inject.Inject

class ListCharactersRepositoryImpl @Inject constructor(
    private val service: ApiService
) : ListCharactersRepository {
    override fun getListCharacters(offset: Int): Observable<CharacterDataContainer> {
        return service
            .getListCharacters(offset = offset)
            .map { it.data }
    }
}