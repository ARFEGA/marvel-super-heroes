package com.costular.marvelheroes.data.net

import com.costular.marvelheroes.data.model.MarvelHeroesResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by costular on 17/03/2018.
 */
interface MarvelHeroesService {
    @GET(".") //Para marvel hero
    fun getMarvelHeroesList(): Flowable<MarvelHeroesResponse>
    //@GET("api/?results=20")

}