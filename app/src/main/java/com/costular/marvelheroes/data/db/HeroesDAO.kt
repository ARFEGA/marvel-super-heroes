package com.costular.marvelheroes.data.db

import android.arch.persistence.room.*
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
abstract class HeroesDAO {

    @Query("select * from heroes")
    abstract fun getAllHeroes():Maybe<List<MarvelHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(heroes: List<MarvelHeroEntity>)

    @Query("DELETE FROM heroes")
    abstract fun deleteAllHeroes()

    @Transaction
    open fun removeAndInsertHeroes(heroes:List<MarvelHeroEntity>){
        deleteAllHeroes()
        insertAll(heroes)
    }
}