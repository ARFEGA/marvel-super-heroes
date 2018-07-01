package com.costular.marvelheroes.domain.model

import android.support.v7.util.DiffUtil

class UserEntityDiff: DiffUtil.ItemCallback<MarvelHeroEntity>() {
    override fun areItemsTheSame(oldItem: MarvelHeroEntity?, newItem: MarvelHeroEntity?): Boolean {
       return oldItem?.name == newItem?.name
    }

    override fun areContentsTheSame(oldItem: MarvelHeroEntity?, newItem: MarvelHeroEntity?): Boolean {
        return oldItem == newItem
    }
}