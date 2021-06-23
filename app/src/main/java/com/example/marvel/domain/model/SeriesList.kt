package com.example.marvel.domain.model

import androidx.room.ColumnInfo

data class SeriesList(
    @ColumnInfo(name = "serie_uri")
    val collectionURI: String?
)