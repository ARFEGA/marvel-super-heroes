package com.costular.marvelheroes.presentation.heroeslist

import android.support.v7.widget.RecyclerView
import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.costular.marvelheroes.R
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.domain.model.UserEntityDiff
import kotlinx.android.synthetic.main.item_hero.*
import kotlinx.android.synthetic.main.item_hero.view.*



typealias OnUserClick = (MarvelHeroEntity, ImageView) -> Unit

class  HeroesListAdapterMVVM(val onUserClick: OnUserClick) : RecyclerView.Adapter<HeroesListAdapterMVVM.HeroesListViewHolder>()
{
    val items = mutableListOf<MarvelHeroEntity>()

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero,parent,false)
        return HeroesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroesListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(newItems : List<MarvelHeroEntity>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


    inner class HeroesListViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bind(marvelHeroEntity: MarvelHeroEntity){
            with(itemView) {
                heroTitle.text = marvelHeroEntity.name
                Glide.with(heroImage_MVVM)
                        .load(marvelHeroEntity.photoUrl)
                        .into(heroImage_MVVM)
                setOnClickListener { onUserClick(marvelHeroEntity, heroImage_MVVM) }
                //ó de eta forma
                //itemView.setOnClickListener { onUserClick.invoke(items[adapterPosition],imageView) }
            }

        }
    }

}





/*
class HeroesListAdapterMVVM : ListAdapter<MarvelHeroEntity,HeroesListAdapterMVVM.HeroesListViewHolder>(UserEntityDiff()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero,parent,false)
        return HeroesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroesListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class HeroesListViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bind(heroesListEntity: MarvelHeroEntity){
            with(itemView){
                heroTitle.text = heroesListEntity.name
                Glide.with(heroImage)
                        .load(heroesListEntity.photoUrl)
                        .into(heroImage)
            }

        }
    }

}*/
