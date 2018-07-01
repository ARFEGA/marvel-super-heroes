package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.costular.marvelheroes.R
import com.costular.marvelheroes.R.id.heroesListLoading_mvvm
import com.costular.marvelheroes.R.id.heroesListRecycler_mvvm
//import com.costular.marvelheroes.di.components.DaggerGetMarvelHeroesListComponent
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.heroedetail.MarvelHeroDetailActivityMVVM
import com.costular.marvelheroes.presentation.util.Navigator

import kotlinx.android.synthetic.main.activity_main_mvvm.*

class HeroesListActivityMVVM : AppCompatActivity(){




    lateinit var heroesListViewModel: HeroesListViewModel

    lateinit var adapter :  HeroesListAdapterMVVM




    override fun onCreate(savedInstanceState: Bundle?) {
        //inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mvvm)
        setUpRecycler()
        setUpViewModel()
    }

    private fun setUpRecycler(){
        adapter = HeroesListAdapterMVVM{ hero, image -> goToHeroDetail(hero, image) }
        heroesListRecycler_mvvm.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        heroesListRecycler_mvvm.itemAnimator = DefaultItemAnimator()
        heroesListRecycler_mvvm.adapter = adapter
    }
    private fun setUpViewModel(){
        heroesListViewModel = ViewModelProviders.of(this).get(HeroesListViewModel::class.java)
        bindEvents()
        heroesListViewModel.loadHeroesList()
    }
    private fun bindEvents(){

        heroesListViewModel.isLoadingState.observe(this, Observer { isLoading ->
            isLoading?.let {
                showLoading(it)
            }
        })

        //JetPack
        heroesListViewModel.heroesListState.observe(this,Observer{ heroesList ->
            heroesList?.let{
                onHeroesListLoaded(it)
            }
        })

        //RxJava
        //heroesListViewModel.heroesListState.subscribe{  onHeroesListLoaded(it) }
    }

    private fun onHeroesListLoaded(heroesList: List<MarvelHeroEntity>){
        adapter.submitList(heroesList)
    }

    private fun showLoading(isLoading : Boolean){
        heroesListLoading_mvvm.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun goToHeroDetail(hero: MarvelHeroEntity, image: View) {
        Navigator.goToHeroDetailMVVM(this, hero, image)
        //Iniciamos la activity de detail sin navigator
       /* val intent = Intent(this,MarvelHeroDetailActivityMVVM::class.java)
        intent.putExtra(MarvelHeroDetailActivityMVVM.PARAM_HERO_ID,hero.name)//Debeo pasar el id, pero por el momento el nombre
        startActivity(intent)*/
    }


}
