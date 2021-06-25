package com.example.marvel.presentation.ui.characterdetail.storierecycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.marvel.domain.model.Storie

class StorieAdapter : ListAdapter<Storie, StorieViewHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorieViewHolder {
        return StorieViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: StorieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<Storie>() {
            override fun areItemsTheSame(oldItem: Storie, newItem: Storie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Storie, newItem: Storie): Boolean {
                return oldItem == newItem
            }
        }
    }
}