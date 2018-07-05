package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe

interface LocalMarvelHeroesDataSource {

    fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>>
    fun getMarvelHeroDetail(name: String) : Maybe<MarvelHeroEntity>
    fun saveHeroes(heroes:List<MarvelHeroEntity>)
    fun updateHeroFavorite(name:String,isFavorite:Boolean)



}