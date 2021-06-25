package com.example.marvel.presentation.ui.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvel.domain.model.Comic
import com.example.marvel.domain.model.Event
import com.example.marvel.domain.model.Serie
import com.example.marvel.domain.model.Storie
import com.example.marvel.domain.repository.ComicRepository
import com.example.marvel.domain.repository.EventRepository
import com.example.marvel.domain.repository.SerieRepository
import com.example.marvel.domain.repository.StorieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repositoryComic: ComicRepository,
    private val repositorySerie: SerieRepository,
    private val repositoryStorie: StorieRepository,
    private val repositoryEvent: EventRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val listComic: LiveData<List<Comic>> get() = _listComic
    private val _listComic: MutableLiveData<List<Comic>> = MutableLiveData()

    val listSerie: LiveData<List<Serie>> get() = _listSerie
    private val _listSerie: MutableLiveData<List<Serie>> = MutableLiveData()

    val listStorie: LiveData<List<Storie>> get() = _listStorie
    private val _listStorie: MutableLiveData<List<Storie>> = MutableLiveData()

    val listEvent: LiveData<List<Event>> get() = _listEvent
    private val _listEvent: MutableLiveData<List<Event>> = MutableLiveData()

    val error: LiveData<String> get() = _error
    private val _error: MutableLiveData<String> = MutableLiveData()

    internal fun getListComic(characterId: Int) {
        repositoryComic
            .getListComic(characterId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listComic.postValue(it.results)
            }) {
                _error.postValue(it.message)
            }
            .let(disposable::add)
    }

    internal fun getListSerie(characterId: Int) {
        repositorySerie
            .getListSerie(characterId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listSerie.postValue(it.results)
            }) {
                _error.postValue(it.message)
            }
            .let(disposable::add)
    }

    internal fun getListStorie(characterId: Int) {
        repositoryStorie
            .getListStorie(characterId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listStorie.postValue(it.results)
            }) {
                _error.postValue(it.message)
            }
            .let(disposable::add)
    }

    internal fun getListEvent(characterId: Int) {
        repositoryEvent
            .getListEvent(characterId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _listEvent.postValue(it.results)
            }) {
                _error.postValue(it.message)
            }
            .let(disposable::add)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}