package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.servicelocator.Inject
import com.costular.marvelheroes.presentation.util.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class HeroDetailViewModel: BaseViewModel(){

    val heroState : MutableLiveData<MarvelHeroEntity> = MutableLiveData()

    /*fun loadHeroByID(usdrID : Long){
        Inject.marvelHeroesRepository.getMarvelHeroDetail(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            heroState.value = it
                        },
                        onError = {

                        },
                        onComplete = {

                        }

                ).addTo(compositeDisposable)//Del BaseViewModel
    }*/

}