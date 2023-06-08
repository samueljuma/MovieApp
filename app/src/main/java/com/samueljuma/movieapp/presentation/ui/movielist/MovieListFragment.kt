package com.samueljuma.movieapp.presentation.ui.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.samueljuma.movieapp.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.navigateToMovieDetails.observe(viewLifecycleOwner){movie->
            movie?.let {
                this.findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment2(movie))
                viewModel.doneNavigatingToMovieDetails()
            }
        }
    }

    private fun subscribeUI(adapter: MovieListAdapter, binding: FragmentMovieListBinding) {

        viewModel.getMovies().observe(viewLifecycleOwner){ movieList ->
            if(movieList !=null){
                binding.movieProgressBar.visibility = View.VISIBLE
                adapter.submitList(movieList)
                binding.movieProgressBar.visibility = View.GONE
            }else{
                Toast.makeText(context,"No data",Toast.LENGTH_SHORT).show()
            }

        }
    }
}