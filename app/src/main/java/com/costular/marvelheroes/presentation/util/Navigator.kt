package com.costular.marvelheroes.presentation.util

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.heroedetail.MarvelHeroDetailActivityMVVM

/**
 * Created by costular on 17/03/2018.
 */
object Navigator {


    fun goToHeroDetailMVVM(activity: Activity, hero: MarvelHeroEntity, image: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, image,
                ViewCompat.getTransitionName(image))
        val intent = Intent(activity, MarvelHeroDetailActivityMVVM::class.java).apply {
            putExtra(MarvelHeroDetailActivityMVVM.PARAM_HEROE, hero)
        }

        activity.startActivity(intent, options.toBundle())
    }

}