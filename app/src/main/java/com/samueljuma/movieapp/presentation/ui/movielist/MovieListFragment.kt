package com.samueljuma.movieapp.presentation.ui.movielist

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.lifecycle.Lifecycle
import com.samueljuma.movieapp.R
import com.samueljuma.movieapp.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.Exception

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var adapter: MovieListAdapter
    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)

        adapter = MovieListAdapter(MovieClickListener { movie ->
            viewModel.onMovieClicked(movie)
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.movieProgressBar.visibility = View.VISIBLE

        subscribeUI(adapter, binding)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * New way of handling Menu as of the writing of this code: June 9th 2023
         */
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_refresh -> {
                        updateUI(adapter, binding)
                        true
                    }

                    R.id.menu_about -> {
                        findNavController().navigate(R.id.action_movieListFragment_to_aboutFragment)
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


        viewModel.navigateToMovieDetails.observe(viewLifecycleOwner) { movie ->
            movie?.let {
                this.findNavController().navigate(
                    MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment2(movie)
                )
                viewModel.doneNavigatingToMovieDetails()
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            updateUI(adapter, binding)
        }

    }

    private fun subscribeUI(adapter: MovieListAdapter, binding: FragmentMovieListBinding) {

        viewModel.getMovies().observe(viewLifecycleOwner) { movieList ->
            if (movieList != null) {
                binding.movieProgressBar.visibility = View.VISIBLE
                adapter.submitList(movieList)
                binding.movieProgressBar.visibility = View.GONE
            } else {
                Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun updateUI(adapter: MovieListAdapter, binding: FragmentMovieListBinding) {

        viewModel.updateMovies().observe(viewLifecycleOwner) { movieList ->
            try {
                if (movieList != null) {
                    binding.movieProgressBar.visibility = View.VISIBLE
                    adapter.submitList(movieList)
                    binding.movieProgressBar.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                    Toast.makeText(context, "Data Refreshed", Toast.LENGTH_SHORT).show()
                    Log.i("Tagy", "Refresh Success")
                } else {
                    Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show()
                }
            } catch (exception: Exception) {
                Log.e("Tagy", "Failed to Refresh", exception)
            }


        }
    }

}