package com.costular.marvelheroes.presentation.util

import android.content.SharedPreferences

class SettingsManager(val sharedPreferences:SharedPreferences) {

    companion object {
        const val FIRST_LOAD_HEROES = "first_load_heroes"
    }


    var isFirstLoadHeroes : Boolean
    get() = sharedPreferences.getBoolean(FIRST_LOAD_HEROES,true)
    set(value) {
        sharedPreferences
                .edit()
                .putBoolean(FIRST_LOAD_HEROES, value)
                .apply()  //Lo hace asincronamente mientras que .commit en el hilo actual
    }


}