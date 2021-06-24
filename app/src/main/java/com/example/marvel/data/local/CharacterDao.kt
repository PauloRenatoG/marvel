package com.example.marvel.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel.domain.model.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Character>)

    @Query("SELECT * FROM character ORDER BY name")
    fun getListCharacter(): DataSource.Factory<Int, Character>
}