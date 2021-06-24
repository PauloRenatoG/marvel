package com.example.marvel.presentation.ui.listcharacters

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.marvel.data.local.AppDataBase
import com.example.marvel.data.repository.paging.CharacterBoundaryCallback
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.repository.ListCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ListCharactersViewModel @Inject constructor(
    private val repository: ListCharactersRepository,
    private val dataBase: AppDataBase
) : ViewModel(), LifecycleObserver {
    private val disposable = CompositeDisposable()
    private val characterBoundaryCallback =
        CharacterBoundaryCallback(dataBase, repository, disposable, ::onError)
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPrefetchDistance(5)
        .setPageSize(20)
        .build()

    val isLoading: LiveData<Boolean> get() = _isLoading
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val listCharacter: LiveData<PagedList<Character>> get() = _listCharacter
    private var _listCharacter: LiveData<PagedList<Character>> = MutableLiveData()

    val error: LiveData<String> get() = _error
    private var _error: MutableLiveData<String> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getListCharacter() {
        _listCharacter = LivePagedListBuilder(
            dataBase.characterDao().getListCharacter(),
            pagedListConfig
        )
            .setBoundaryCallback(characterBoundaryCallback)
            .build()
    }

    private fun onError(throwable: Throwable) {
        _error.postValue(throwable.message)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}