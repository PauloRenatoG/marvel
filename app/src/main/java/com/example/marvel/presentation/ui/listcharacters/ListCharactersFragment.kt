package com.example.marvel.presentation.ui.listcharacters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.example.marvel.databinding.FragmentListCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCharactersFragment : Fragment() {

    private var binding: FragmentListCharactersBinding? = null
    private val viewModel: ListCharactersViewModel by viewModels()
    private val adapterCharacters = ListCharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentListCharactersBinding.inflate(inflater, container, false)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding?.toolbar)
        lifecycle.addObserver(viewModel)
        subscribeUi()

        return binding?.root
    }

    private fun subscribeUi() {
        with(viewModel) {
            listCharacter.observe(viewLifecycleOwner, {
                adapterCharacters.submitData(lifecycle, it)
            })
            isLoading.observe(viewLifecycleOwner, { isLoading ->
                binding?.loading?.visibility = if (isLoading) View.VISIBLE else View.GONE
            })
            error.observe(viewLifecycleOwner, { error ->
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            })
        }
        binding?.recyclerCharacter?.adapter = adapterCharacters
        adapterCharacters.addLoadStateListener { state ->
            binding?.loading?.visibility =
                if (state.refresh is LoadState.Loading) View.VISIBLE else View.GONE
        }
    }
}