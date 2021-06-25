package com.example.marvel.data.remote

import com.example.marvel.domain.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    fun getListCharacters(
        @Query("ts") timestamp: Int = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("offset") offset: Int? = 0
    ): Observable<DataWrapper<DataContainer<Character>>>

    @GET("characters/{characterId}/comics")
    fun getListComics(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: Int = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH
    ): Observable<DataWrapper<DataContainer<Comic>>>

    @GET("characters/{characterId}/series")
    fun getListSeries(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: Int = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH
    ): Observable<DataWrapper<DataContainer<Serie>>>

    @GET("characters/{characterId}/stories")
    fun getListStories(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: Int = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH
    ): Observable<DataWrapper<DataContainer<Storie>>>

    @GET("characters/{characterId}/events")
    fun getListEvents(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: Int = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH
    ): Observable<DataWrapper<DataContainer<Event>>>

    companion object {
        private const val TIMESTAMP = 1
        private const val API_KEY = "ddc37ea1e0cb546c1c476c5bf2f7d778"
        private const val HASH = "04905e9d12986cd8816ab2102e752f18"
    }
}