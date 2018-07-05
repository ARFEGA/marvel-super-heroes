package com.costular.marvelheroes.data.repository

import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.repository.datasource.APIMarvelHeroesDataSourceMVVM
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSourceMVVM
import com.costular.marvelheroes.data.repository.datasource.MarvelHeroesDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.SettingsManager
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import org.intellij.lang.annotations.Flow

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryImpl(private val localDataSource : LocalMarvelHeroesDataSourceMVVM,
                                 private val apiMarvelHeroesDataSource:APIMarvelHeroesDataSourceMVVM,
                                 private val marvelHeroesMapper: MarvelHeroMapper,
                                 private val settingsManager: SettingsManager)
    : MarvelHeroesRepository {

    override fun getMarvelHeroesList(): Flowable<List<MarvelHeroEntity>> =
        if(settingsManager.isFirstLoadHeroes) {
            getHeroesFromDB()
                    .concatWith(
                            getHeroesFromAPI()
                    )
        } else getHeroesFromDB()


    private fun getHeroesFromDB(): Flowable<List<MarvelHeroEntity>> = localDataSource.getMarvelHeroesList()

    private fun getHeroesFromAPI(): Flowable<List<MarvelHeroEntity>> =
            apiMarvelHeroesDataSource.getMarvelHeroesList()
                    .map { marvelHeroesMapper.transformList(it) }
                    .doOnNext { localDataSource.saveHeroes(it) }


    override fun updateHeroRow(name:String,isFavorite:Boolean){
        localDataSource.updateHeroFavorite(name,isFavorite)
    }


   override fun getMarvelHeroDetail(name: String ): Maybe<MarvelHeroEntity> =
            localDataSource
                    .getMarvelHeroDetail(name)
}