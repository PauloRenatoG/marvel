package com.example.marvel.presentation.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.databinding.FragmentCharacterDetailBinding
import com.example.marvel.presentation.ui.characterdetail.comicrecycler.ComicAdapter
import com.example.marvel.presentation.ui.characterdetail.eventrecycler.EventAdapter
import com.example.marvel.presentation.ui.characterdetail.serierecycler.SerieAdapter
import com.example.marvel.presentation.ui.characterdetail.storierecycler.StorieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var binding: FragmentCharacterDetailBinding? = null
    private val args by navArgs<CharacterDetailFragmentArgs>()
    private val character by lazy { args.character }
    private val comicAdapter = ComicAdapter()
    private val serieAdapter = SerieAdapter()
    private val storieAdapter = StorieAdapter()
    private val eventAdapter = EventAdapter()
    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        subscribeUi()
        setUpRecyclers()
        setImage()

        return binding?.root
    }

    private fun subscribeUi() {
        character?.id?.let {
            viewModel.getListComic(it)
            viewModel.getListSerie(it)
            viewModel.getListStorie(it)
            viewModel.getListEvent(it)
        }
        binding?.run {
            textNameCharacter.text = character?.name
            if (!character?.description.isNullOrEmpty()) {
                textDescription.text = character?.description
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
        viewModel.listComic.observe(viewLifecycleOwner) {
            binding?.progressComic?.visibility = View.GONE
            comicAdapter.submitList(it)
        }
        viewModel.listSerie.observe(viewLifecycleOwner) {
            binding?.progressSerie?.visibility = View.GONE
            serieAdapter.submitList(it)
        }
        viewModel.listStorie.observe(viewLifecycleOwner) {
            binding?.progressStorie?.visibility = View.GONE
            storieAdapter.submitList(it)
        }
        viewModel.listEvent.observe(viewLifecycleOwner) {
            binding?.progressEvent?.visibility = View.GONE
            eventAdapter.submitList(it)
        }
    }

    private fun setUpRecyclers() {
        binding?.run {
            recyclerComic.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerComic.adapter = comicAdapter

            recyclerSerie.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerSerie.adapter = serieAdapter

            recyclerStories.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerStories.adapter = storieAdapter

            recyclerEvents.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerEvents.adapter = eventAdapter
        }
    }

    private fun setImage() {
        binding?.imageCharacter?.let {
            Glide.with(requireContext())
                .load(character?.thumbnail?.path?.plus("/portrait_medium.jpg"))
                .placeholder(R.drawable.marvel_logo)
                .centerCrop()
                .into(it)
        }
    }
}