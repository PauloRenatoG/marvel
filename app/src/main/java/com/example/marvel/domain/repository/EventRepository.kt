package com.example.marvel.domain.repository

import com.example.marvel.domain.model.DataContainer
import com.example.marvel.domain.model.Event
import io.reactivex.Observable

interface EventRepository {
    fun getListEvent(characterId: Int): Observable<DataContainer<Event>>
}