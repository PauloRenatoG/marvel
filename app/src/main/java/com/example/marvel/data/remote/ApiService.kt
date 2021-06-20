package com.example.marvel.data.remote

import com.example.marvel.domain.model.CharacterDataContainer
import com.example.marvel.domain.model.DataWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    fun getListCharacters(
        @Query("ts") timestamp: Int = TIMESTAMP,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("hash") hash: String = HASH,
    ): Observable<DataWrapper<CharacterDataContainer>>

    companion object {
        private const val TIMESTAMP = 1
        private const val API_KEY = "ddc37ea1e0cb546c1c476c5bf2f7d778"
        private const val HASH = "04905e9d12986cd8816ab2102e752f18"
    }
}