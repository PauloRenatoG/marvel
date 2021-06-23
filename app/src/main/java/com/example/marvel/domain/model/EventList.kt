package com.example.marvel.domain.model

import androidx.room.ColumnInfo

data class EventList(
    @ColumnInfo(name = "event_uri")
    val collectionURI: String?
)