package com.example.marvel.presentation.ui.listcharacters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marvel.domain.model.Character

class ListCharactersAdapter(
    private val onItemClicked: (Character?) -> Unit
) : PagedListAdapter<Character, ListCharactersViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCharactersViewHolder {
        return ListCharactersViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: ListCharactersViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}