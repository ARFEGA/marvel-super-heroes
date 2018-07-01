package com.costular.marvelheroes.presentation

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.costular.marvelheroes.di.components.ApplicationComponent
//import com.costular.marvelheroes.di.components.DaggerApplicationComponent
import com.costular.marvelheroes.di.modules.ApplicationModule
import com.costular.marvelheroes.presentation.servicelocator.Inject
import com.costular.marvelheroes.presentation.util.SettingsManager
import com.facebook.stetho.Stetho

/**
 * Created by costular on 16/03/2018.
 */
class MainApp : Application() {


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        Inject.initDB(this)
        Inject.settingsManager = SettingsManager(PreferenceManager.getDefaultSharedPreferences(this))
    }
//    val component: ApplicationComponent by lazy {
//        DaggerApplicationComponent.builder()
//                .applicationModule(ApplicationModule(this))
//                .build()
//    }

}