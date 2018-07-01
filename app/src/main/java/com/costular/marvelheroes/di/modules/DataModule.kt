package com.costular.marvelheroes.di.modules

import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.datasource.APIMarvelHeroesDataSourceMVVM
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by costular on 17/03/2018.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMarvelHeroMapper(): MarvelHeroMapper = MarvelHeroMapper()

    @Provides
    @Singleton
    fun provideRemoteMarvelHeroesDataSoruce(marvelHeroesService: MarvelHeroesService)
            : APIMarvelHeroesDataSourceMVVM =
            APIMarvelHeroesDataSourceMVVM(marvelHeroesService)

//    @Provides
//    @Singleton
//    fun provideMarvelHeroesRepository(
//            fakeMarvelRemoteMarvelHeroesDataSource: FakeMarvelHeroesDataSource,
//            marvelHeroMapper: MarvelHeroMapper): MarvelHeroesRepositoryImpl =
//            MarvelHeroesRepositoryImpl(fakeMarvelRemoteMarvelHeroesDataSource, marvelHeroMapper)
////    @Provides
////    @Singleton
////    fun provideMarvelHeroesRepository(
////            marvelRemoteMarvelHeroesDataSource: APIMarvelHeroesDataSourceMVVM,
////            marvelHeroMapper: MarvelHeroMapper): MarvelHeroesRepositoryImpl =
////    MarvelHeroesRepositoryImpl(marvelRemoteMarvelHeroesDataSource, marvelHeroMapper)

}