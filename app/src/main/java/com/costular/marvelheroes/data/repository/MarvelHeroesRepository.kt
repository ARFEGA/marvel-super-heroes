package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by costular on 17/03/2018.
 */
interface MarvelHeroesRepository {

    fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>>
    fun getMarvelHeroDetail(name: String) : Maybe<MarvelHeroEntity>

}