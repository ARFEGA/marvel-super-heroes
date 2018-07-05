package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
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


    fun updateFavoriteByName(name:String,isFavorite:Boolean){
        Inject.localDataSourceMVVM.updateHeroFavorite(name,isFavorite)
    }

    fun loadHeroByName(name : String){
        Inject.marvelHeroesRepository.getMarvelHeroDetail(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess ={
                            heroState.value = it
                        },
                        onError = {
                            Log.d("DETAILViewModel",it.toString())
                        },
                        onComplete = {

                        }

                ).addTo(compositeDisposable)//Del BaseViewModel
    }

}