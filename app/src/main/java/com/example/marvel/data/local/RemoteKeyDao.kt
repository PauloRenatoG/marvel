package com.example.marvel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvel.domain.model.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys ORDER BY id DESC")
    fun remoteKeyByQuery(): List<RemoteKey>

    @Query("DELETE FROM remote_keys WHERE id = :query")
    fun deleteByQuery(query: Int)
}