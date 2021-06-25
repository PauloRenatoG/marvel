package com.example.marvel.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "character")
data class Character(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val description: String?,
    @Embedded
    val thumbnail: Image?,
    @Embedded
    val comics: ComicList?,
    @Embedded
    val series: SeriesList?,
    @Embedded
    val stories: StoryList?,
    @Embedded
    val events: EventList?
) : Serializable