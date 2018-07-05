package com.costular.marvelheroes.presentation.heroedetail

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.design.R.attr.tint
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.graphics.toColor
import com.bumptech.glide.Glide
import com.costular.marvelheroes.R
import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.heroeslist.HeroesListViewModel
import kotlinx.android.synthetic.main.activity_hero_detail_mvvm.*
import kotlinx.android.synthetic.main.item_hero.*

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

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        //supportPostponeEnterTransition() // Wait for image load and then draw the animation


        marvelHeroEntity = intent.getParcelableExtra(PARAM_HEROE)
        //Paso 2 empleo de LiveData desde activity/Fragment
        heroDetailMVVM = ViewModelProviders.of(this).get(HeroDetailViewModel::class.java)
        bindEvents()
        loadHeroData()

        favorite.setOnClickListener {
            marvelHeroEntity?.let { hero ->
                updateDB(hero)
                bindEvents()
                heroDetailMVVM.loadHeroByName(hero.name)
                //updateFavoriteIcon(!hero.favorite)
            }
        }
    }
    private fun updateDB(hero:MarvelHeroEntity){
        heroDetailMVVM.updateFavoriteByName(hero.name, !hero.favorite)
        Toast.makeText(this, "Favorite:${ !hero.favorite}", Toast.LENGTH_LONG).show()




    }

    private fun updateFavoriteIcon(isFavorite:Boolean){
            if (isFavorite)
                favorite.setImageResource(R.drawable.favorite)
            else
                favorite.setImageResource(R.drawable.no_favorite)
    }

       //favorite_false.visibility =  if(favorite_false.visibility == View.VISIBLE) View.GONE else View.VISIBLE

    private fun bindEvents(){
        //Paso 4 empleo de LiveData desde activity/Fragment
        heroDetailMVVM.heroState.observe(this, Observer {hero ->
            hero?.let {
                marvelHeroEntity = it
                onHeroLoaded(it)
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
        updateFavoriteIcon(hero.favorite)
        Glide.with(this)
                .load(hero.photoUrl)
                .into(heroDetailImage_mvvm)

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}