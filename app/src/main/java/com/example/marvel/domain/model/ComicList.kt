package com.example.marvel.domain.model

import androidx.room.ColumnInfo

data class ComicList(
    @ColumnInfo(name = "comic_uri")
    val collectionURI: String?
)