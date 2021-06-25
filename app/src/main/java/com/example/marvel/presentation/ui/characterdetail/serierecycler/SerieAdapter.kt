package com.example.marvel.presentation.ui.characterdetail.serierecycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.marvel.domain.model.Serie

class SerieAdapter : ListAdapter<Serie, SerieViewHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        return SerieViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<Serie>() {
            override fun areItemsTheSame(oldItem: Serie, newItem: Serie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Serie, newItem: Serie): Boolean {
                return oldItem == newItem
            }
        }
    }
}