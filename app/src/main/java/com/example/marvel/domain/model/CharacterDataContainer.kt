package com.example.marvel.domain.model

data class CharacterDataContainer(
    val offset: Int?,
    val limit: Int?,
    val results: List<Character>?
)