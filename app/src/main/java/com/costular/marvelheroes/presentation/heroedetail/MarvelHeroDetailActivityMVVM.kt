package com.costular.marvelheroes.presentation.heroedetail

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.costular.marvelheroes.R
import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import kotlinx.android.synthetic.main.activity_hero_detail_mvvm.*

class MarvelHeroDetailActivityMVVM : AppCompatActivity(){


    companion object {
        const val PARAM_HERO_ID = "hero_id"
        const val PARAM_HEROE = "heroe"
    }
    //Paso 1 empleo de LiveData desde activity/Fragment
    lateinit var heroDetailMVVM : HeroDetailViewModel
    //var heroId : Long = -1
    var marvelHeroEntity : MarvelHeroEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail_mvvm)
        marvelHeroEntity = intent.getParcelableExtra(PARAM_HEROE)

        //Paso 2 empleo de LiveData desde activity/Fragment
        heroDetailMVVM = ViewModelProviders.of(this).get(HeroDetailViewModel::class.java)
        bindEvents()
        loadHeroData()
    }

    private fun bindEvents(){
        //Paso 4 empleo de LiveData desde activity/Fragment
        heroDetailMVVM.heroState.observe(this, Observer {hero ->
            hero?.let {
                onHeroLoaded(hero)
            }

        })
    }
    private fun loadHeroData(){
        if(marvelHeroEntity == null){
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        //Paso 3 empleo de LiveData desde activity/Fragment
        onHeroLoaded(marvelHeroEntity!!)
    }

    private fun onHeroLoaded(hero : MarvelHeroEntity){
        heroDetailName_mvvm.text = hero.name
        Glide.with(this)
                .load(hero.photoUrl)
                .into(heroDetailImage_mvvm)

    }
}