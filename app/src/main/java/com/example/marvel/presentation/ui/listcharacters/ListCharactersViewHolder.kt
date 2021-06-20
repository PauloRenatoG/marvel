package com.example.marvel.presentation.ui.listcharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.databinding.ListItemCharacterBinding
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.model.Image

class ListCharactersViewHolder(
    private val binding: ListItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        binding.run {
            textName.text = character.name
            setImage(character.thumbnail)
        }
    }

    private fun setImage(thumbnail: Image?) {
        Glide.with(binding.root.context)
            .load(thumbnail?.path?.plus("/landscape_xlarge.jpg"))
            .centerCrop()
            .into(binding.imageView)
    }

    companion object {
        fun inflate(parent: ViewGroup): ListCharactersViewHolder {
            return ListCharactersViewHolder(
                ListItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}