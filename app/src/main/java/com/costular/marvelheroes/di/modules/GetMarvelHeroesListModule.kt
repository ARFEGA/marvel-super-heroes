package com.costular.marvelheroes.di.modules

import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.di.scopes.PerActivity
import com.costular.marvelheroes.domain.usecase.GetMarvelHeroesList
import com.costular.marvelheroes.presentation.heroeslist.HeroesListActivityMVVM
import dagger.Module
import dagger.Provides

/**
 * Created by costular on 17/03/2018.
 */
@Module
class GetMarvelHeroesListModule(private val view: HeroesListActivityMVVM) {

//    @Provides
//    @PerActivity
//    fun provideView(): HeroesListContract.View = view

    @Provides
    @PerActivity
    fun provideGetListHeroesUseCase(marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl): GetMarvelHeroesList =
            GetMarvelHeroesList(marvelHeroesRepositoryImpl)

}