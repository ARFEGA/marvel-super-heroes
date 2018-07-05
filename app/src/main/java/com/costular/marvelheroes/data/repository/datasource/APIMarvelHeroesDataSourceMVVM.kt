package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 * Created by costular on 17/03/2018.
 */
class APIMarvelHeroesDataSourceMVVM(private val marvelHeroesService: MarvelHeroesService):
        MarvelHeroesDataSource {
    override fun getMarvelHeroDetail(name: String): Maybe<MarvelHeroEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getMarvelHeroesList(): Flowable<List<MarvelHero>> =
            marvelHeroesService.getMarvelHeroesList().map { it.superheroes }






}