package com.costular.marvelheroes.repository

import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.data.repository.datasource.APIMarvelHeroesDataSourceMVVM
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroesRepositoryTest {

    private val mockAPIDataSourceMVVM: APIMarvelHeroesDataSourceMVVM = mock()

    private lateinit var mapper: MarvelHeroMapper
    private lateinit var marvelHeroesRepository: MarvelHeroesRepositoryImpl

    @Before
    fun setUp() {
        mapper = MarvelHeroMapper()
        marvelHeroesRepository = MarvelHeroesRepositoryImpl(mockAPIDataSourceMVVM, mapper)
    }

    @Test
    fun `repository should retrieve marvel heroes list`() {
        val heroes = listOf(MarvelHero("Iron Man"), MarvelHero("Spider-Man"))
        val observable = Observable.just(heroes)
        whenever(mockAPIDataSourceMVVM.getMarvelHeroesList()).thenReturn(observable)

        val result = marvelHeroesRepository.getMarvelHeroesList()

        verify(mockAPIDataSourceMVVM).getMarvelHeroesList()

        result.test()
                .assertValue { it.size == 2 }
                .assertValue { it.first().name == heroes.first().name }
                .assertValue { it.last().name == heroes.last().name }
    }

}