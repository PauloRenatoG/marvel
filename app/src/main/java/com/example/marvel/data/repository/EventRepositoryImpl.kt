package com.example.marvel.data.repository

import com.example.marvel.data.remote.ApiService
import com.example.marvel.domain.model.DataContainer
import com.example.marvel.domain.model.Event
import com.example.marvel.domain.repository.EventRepository
import io.reactivex.Observable
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val service: ApiService
) : EventRepository {
    override fun getListEvent(characterId: Int): Observable<DataContainer<Event>> {
        return service
            .getListEvents(characterId = characterId)
            .map { it.data }
    }
}