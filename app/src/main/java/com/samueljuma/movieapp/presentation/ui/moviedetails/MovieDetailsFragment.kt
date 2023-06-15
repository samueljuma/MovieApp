package com.samueljuma.movieapp.presentation.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.samueljuma.movieapp.R
import com.samueljuma.movieapp.data.model.Movie
import com.samueljuma.movieapp.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val arguments: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)

        viewModel.setMovie(arguments.movie)

        viewModel.movie.observe(viewLifecycleOwner){movie ->
            binding.movie = movie
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         * New way of handling Menu as of the writing of this code: June 9th 2023
         */
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.details_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.menu_add_to_watchlist -> {
                        Toast.makeText(context, "Add Under Construction", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }

        }

        requireActivity().addMenuProvider(menuProvider,viewLifecycleOwner, Lifecycle.State.RESUMED)

        /**
         * End of Menu handling
         */
    }

}