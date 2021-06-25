package com.example.marvel.presentation.ui.characterdetail.eventrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.databinding.ListItemDetailBinding
import com.example.marvel.domain.model.Event
import com.example.marvel.domain.model.Image

class EventViewHolder(
    private val binding: ListItemDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: Event) {
        binding.textNameComic.text = event.title
        setImage(event.thumbnail)
    }

    private fun setImage(thumbnail: Image?) {
        Glide.with(binding.imageComic.context)
            .load(thumbnail?.path?.plus("/portrait_medium.jpg"))
            .placeholder(R.drawable.marvel_logo)
            .centerCrop()
            .into(binding.imageComic)
    }

    companion object {
        fun inflate(parent: ViewGroup): EventViewHolder {
            return EventViewHolder(
                ListItemDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}