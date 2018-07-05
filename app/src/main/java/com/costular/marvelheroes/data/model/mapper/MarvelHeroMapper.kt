package com.costular.marvelheroes.data.model.mapper

import android.arch.persistence.room.TypeConverter
import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.google.gson.Gson
import kotlin.collections.ArrayList

/**
 * Created by costular on 17/03/2018.
 */
class MarvelHeroMapper : Mapper<MarvelHero, MarvelHeroEntity> {

    override fun transform(input: MarvelHero): MarvelHeroEntity =
            MarvelHeroEntity(
                    0,
                    input.name,
                    input.photoUrl,
                    input.realName,
                    input.height,
                    input.power,
                    input.abilities,
                    //input.groups,
                    getGroupsFromMarvelHero(input),
                    false


            )

    override fun transformList(inputList: List<MarvelHero>): List<MarvelHeroEntity> =
            inputList.map { transform(it) }


    private fun getGroupsFromMarvelHero(marvelHero: MarvelHero): String {
        val ArrayGroups : Array<String>  =  marvelHero.groups.replace("\\s".toRegex(), "").split(",").toTypedArray()
        return ListToJson(ArrayGroups)

    }
    @TypeConverter
    private fun ListToJson(list:Array<String>):String {
        val gson = Gson()
        val json = gson.toJson(list);
        return json
    }
}

