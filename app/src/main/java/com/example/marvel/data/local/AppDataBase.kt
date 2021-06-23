package com.example.marvel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.model.RemoteKey

@Database(entities = [Character::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeyDao

    companion object {
        private const val DATABASE_NAME = "marvel-db"

        fun build(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}