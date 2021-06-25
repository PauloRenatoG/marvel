package com.example.marvel.domain.model

data class DataContainer<out T : Any>(
    val offset: Int?,
    val limit: Int?,
    val results: List<T>?
)