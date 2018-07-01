package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.db.HeroesDataBase
import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.data.repository.MarvelHeroesRepository
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class LocalMarvelHeroesDataSourceMVVM(private val heroesDB : HeroesDataBase):MarvelHeroesRepository {
    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> {
            return heroesDB
                    .getHeroesDAO()
                    .getAllHeroes()
                    .toFlowable()
        }
    fun saveHeroes(heroes:List<MarvelHeroEntity>){
        //En segundo plano siempre el insert
        Observable.fromCallable{
            heroesDB.getHeroesDAO().removeAndInsertHeroes(heroes)
        }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    }


