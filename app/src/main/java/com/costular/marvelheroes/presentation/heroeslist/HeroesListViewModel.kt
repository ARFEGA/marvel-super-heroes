package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.servicelocator.Inject
import com.costular.marvelheroes.presentation.util.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HeroesListViewModel: BaseViewModel() {//En BaseViewModel, implementamos CompositeDisposable

    //JetPack
    val heroesListState : MutableLiveData<List<MarvelHeroEntity>> = MutableLiveData()
    val isLoadingState : MutableLiveData<Boolean> = MutableLiveData()
    //RxJava
    //val heroesListState : PublishSubject<List<MarvelHeroEntity>> = PublishSubject.create()
    //val isLoadingState : PublishSubject<Boolean> = PublishSubject()

    fun loadHeroesList(){
        Inject.marvelHeroesRepository.getMarvelHeroesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoadingState.postValue(true) }
                .doOnTerminate {  isLoadingState.postValue(false)}
                .subscribeBy(
                        onNext = {
                            heroesListState.value = it //JetPack
                            //heroesListState.onNext(it) //RxJava
                        },
                        onError = {
                            Log.d("HeroesViewModel",it.toString())
                        },
                        onComplete = {//Para indicar la primera carga de datos
                            Inject.settingsManager.isFirstLoadHeroes = false
                        }
                ).addTo(compositeDisposable)//Creado en abstract class BaseViewModel
    }

}