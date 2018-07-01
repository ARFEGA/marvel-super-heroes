package com.costular.marvelheroes.di.modules

import com.costular.marvelheroes.BuildConfig
import com.costular.marvelheroes.data.net.MarvelHeroesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by costular on 17/03/2018.
 */
@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

       val client =
                OkHttpClient.Builder()
                        //Inercepta la llamada y logea todo_ lo que tiene que ver con body.
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                        //Se pueden medir otro intercepto, como por ejemplo enviar token en cabecera.

        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(client)//EL CLIENTE OKHTTP, ES EL QUE GESTIONA LA LLAMADA POR DEBAJO DE RETROFIT
                                //SI NO LO INDICAMOS, RETROFIT, CREA UNO PROPIO POR DEFECTO
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }

    @Provides
    @Singleton
    fun provideMarvelHeroesService(retrofit: Retrofit): MarvelHeroesService =
            retrofit.create(MarvelHeroesService::class.java)

}