package com.example.marvel.domain.model

data class Character(
    val id: Int?,
    val name: String?,
    val thumbnail: Image?,
    val comics: ComicList?,
    val series: SeriesList?,
    val stories: StoryList?,
    val events: EventList?
)