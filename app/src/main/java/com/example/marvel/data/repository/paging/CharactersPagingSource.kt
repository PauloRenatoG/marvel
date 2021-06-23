package com.example.marvel.data.repository.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.marvel.data.remote.ApiService
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.model.CharacterDataContainer
import com.example.marvel.domain.model.DataWrapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CharactersPagingSource(
    private val service: ApiService
) : RxPagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition: Int = state.anchorPosition ?: return null

        val anchorPage = state.closestPageToPosition(anchorPosition)
            ?: return null

        if (anchorPage.prevKey != null) {
            return anchorPage.prevKey!! + 1
        }

        if (anchorPage.nextKey != null) {
            return anchorPage.nextKey!! - 1
        }
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Character>> {
        var nextPageNumber: Int? = params.key
        if (nextPageNumber == null) {
            nextPageNumber = 0
        }

        return service.getListCharacters(offset = nextPageNumber)
            .subscribeOn(Schedulers.io())
            .map(this::toLoadResult)
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(response: DataWrapper<CharacterDataContainer>): LoadResult<Int, Character> {
        val list: List<Character> = response.data?.results!!
        return LoadResult.Page(
            list,
            null,
            response.data.offset?.plus(21)
        )
    }
}