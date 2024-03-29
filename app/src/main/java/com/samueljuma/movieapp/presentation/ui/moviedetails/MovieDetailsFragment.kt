package com.samueljuma.movieapp.presentation.ui.moviedetails

import android.app.AlertDialog
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
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
import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.databinding.FragmentMovieDetailsBinding
import com.samueljuma.movieapp.presentation.ui.watchlist.WatchListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import java.lang.Exception
import java.sql.SQLException

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val arguments: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModels()

    private val watchListViewModel: WatchListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)

        viewModel.setMovie(arguments.movie)

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
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

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {// Unsubscribe from the observation to prevent further updates
                    watchListViewModel.isMovieInWatchList.removeObservers(viewLifecycleOwner)
                return when (menuItem.itemId) {
                    R.id.menu_add_to_watchlist -> {
                        val movie = arguments.movie
                        val movieToWatch = viewModel.movieToToWatchMovie(movie)

                        watchListViewModel.isMovieInToWatchList(movieToWatch.id)
                        watchListViewModel.isMovieInWatchList.observe(viewLifecycleOwner){ exists->
                            exists?.let {
                                if (exists){
                                    Toast.makeText(context, "Movie Exists", Toast.LENGTH_SHORT).show()
                                }else{
                                    addMovieToWatchList(movieToWatch)
                                }

                                // Unsubscribe from the observation to prevent further updates
                                watchListViewModel.isMovieInWatchList.removeObservers(viewLifecycleOwner)
                            }
                        }
                            true

                    }

                    else -> false
                }
            }

        }

        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

        /**
         * End of Menu handling
         */


    }

    private fun addMovieToWatchList(movieToWatch: MovieToWatch) {
        AlertDialog.Builder(activity).apply {
            setTitle("Add to WatchList")
            setMessage("You are about to add this movie to your WatchList? ")
            setPositiveButton("Add") { _, _ ->
                watchListViewModel.addToWatchList(movieToWatch)
                Toast.makeText(
                    requireActivity(),
                    "${movieToWatch.title} was Added to Watchlist",
                    Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }



}