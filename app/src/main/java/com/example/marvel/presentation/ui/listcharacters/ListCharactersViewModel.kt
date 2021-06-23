package com.example.marvel.presentation.ui.listcharacters

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.repository.ListCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ListCharactersViewModel @Inject constructor(
    private val repository: ListCharactersRepository
) : ViewModel(), LifecycleObserver {
    private val disposable = CompositeDisposable()

    val isLoading: LiveData<Boolean> get() = _isLoading
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val listCharacter: LiveData<PagingData<Character>> get() = _listCharacter
    private var _listCharacter: MutableLiveData<PagingData<Character>> = MutableLiveData()

    val error: LiveData<String> get() = _error
    private var _error: MutableLiveData<String> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getListCharacter() {
        repository
            .getListCharacters()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listCharacter.postValue(it)
            }, {
                _error.postValue(it.message)
            })
            .let(disposable::add)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}