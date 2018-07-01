package com.costular.marvelheroes.di.components

import com.costular.marvelheroes.di.modules.GetMarvelHeroesListModule
import com.costular.marvelheroes.di.scopes.PerActivity
import dagger.Component

/**
 * Created by costular on 17/03/2018.
 */
@PerActivity
@Component(modules = [GetMarvelHeroesListModule::class], dependencies = [ApplicationComponent::class])
interface GetMarvelHeroesListComponent {

    //fun inject(marvelListActivity: HeroesListActivity)

}