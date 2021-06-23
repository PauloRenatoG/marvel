package com.example.marvel.domain.model

import androidx.room.ColumnInfo

data class StoryList(
    @ColumnInfo(name = "story_uri")
    val collectionURI: String?
)