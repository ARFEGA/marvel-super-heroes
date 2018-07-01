package com.costular.marvelheroes.presentation.servicelocator

import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.costular.marvelheroes.BuildConfig
import com.costular.marvelheroes.data.db.HeroesDataBase
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.net.MarvelHeroesService
import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.data.repository.datasource.APIMarvelHeroesDataSourceMVVM
import com.costular.marvelheroes.data.repository.datasource.LocalMarvelHeroesDataSourceMVVM
import com.costular.marvelheroes.presentation.util.SettingsManager
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.arch.persistence.db.SupportSQLiteDatabase



object Inject {

    //Creamos la db
    lateinit var db : HeroesDataBase

    //Creamos retrofit
    val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            //.baseUrl("https://api.myjson.com/bins/bvyob/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    //Creamos sevicio
    val marvelHeroesService = retrofit.create(MarvelHeroesService::class.java)
    //Mapper
    private val marvelHeroMapper = MarvelHeroMapper()

    lateinit var settingsManager: SettingsManager

    lateinit var localDataSourceMVVM : LocalMarvelHeroesDataSourceMVVM//(db)

    private  val apiDataSource = APIMarvelHeroesDataSourceMVVM(marvelHeroesService)

    lateinit var marvelHeroesRepository : MarvelHeroesRepositoryImpl//(localDataSourceMVVM, apiDataSource,marvelHeroMapper)

    fun initDB(context: Context){
        db = Room.databaseBuilder(context,HeroesDataBase::class.java,"heroes.db")
                //.disableMigrationsChecking()
                .fallbackToDestructiveMigration()  //.addMigrations(Migration(1,3))
                .build()
        localDataSourceMVVM = LocalMarvelHeroesDataSourceMVVM(db)
        marvelHeroesRepository = MarvelHeroesRepositoryImpl(localDataSourceMVVM, apiDataSource,marvelHeroMapper)



    }



}