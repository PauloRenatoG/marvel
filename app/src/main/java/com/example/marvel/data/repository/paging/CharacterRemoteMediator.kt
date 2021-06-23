package com.example.marvel.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.example.marvel.data.local.AppDataBase
import com.example.marvel.data.remote.ApiService
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.model.RemoteKey
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException


@ExperimentalPagingApi
class CharacterRemoteMediator(
    private val database: AppDataBase,
    private val service: ApiService
) : RxRemoteMediator<Int, Character>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): Single<MediatorResult> {
        var loadKey: RemoteKey? = when (loadType) {
            LoadType.REFRESH -> {
                RemoteKey(nextKey = null)
            }
            LoadType.PREPEND ->
                return Single.just(MediatorResult.Success(true))
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    return Single.just(MediatorResult.Success(true))
                }
                database.remoteKeysDao().remoteKeyByQuery().firstOrNull()
            }
        }

        if (loadType != LoadType.REFRESH && loadKey == null) {
            return Single.just(MediatorResult.Success(true))
        }
        return service
            .getListCharacters(offset = loadKey?.nextKey)
            .subscribeOn(Schedulers.io())
            .map { response ->
                database.runInTransaction {
                    database.remoteKeysDao()
                        .insertOrReplace(
                            RemoteKey(
                                id = loadKey?.id,
                                nextKey = response.data?.offset?.plus(21)
                            )
                        )
                    response.data?.results?.let { database.characterDao().insertAll(it) }
                }
                response.data?.results?.isNullOrEmpty()?.let { MediatorResult.Success(it) }
            }
            .cast(MediatorResult::class.java)
            .onErrorResumeNext {
                if (it is IOException || it is HttpException) {
                    return@onErrorResumeNext Single.just(MediatorResult.Error(it))
                }
                Single.error(it)
            }

    }
}