package com.example.marvel.data.repository.paging

import androidx.paging.PagedList
import com.example.marvel.data.local.AppDataBase
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.repository.ListCharactersRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterBoundaryCallback(
    private val database: AppDataBase,
    private val repository: ListCharactersRepository,
    private val disposable: CompositeDisposable,
    private val error: (Throwable) -> Unit
) : PagedList.BoundaryCallback<Character>() {

    private var offset = 0

    override fun onZeroItemsLoaded() {
        getListCharacter()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Character) {
        getListCharacter()
    }

    private fun getListCharacter() {
        repository
            .getListCharacters(offset)
            .subscribeOn(Schedulers.io())
            .subscribe({
                database.runInTransaction {
                    it.results?.let { list -> database.characterDao().insertAll(list) }
                }
                offset = offset.plus(21)
            }, {
                error.invoke(it)
            })
            .let(disposable::add)
    }
}