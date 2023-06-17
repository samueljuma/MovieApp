package com.samueljuma.movieapp.presentation.ui.watchlist

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.samueljuma.movieapp.R
import com.samueljuma.movieapp.databinding.FragmentWatchListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchListFragment : Fragment() {

    private lateinit var binding:FragmentWatchListBinding
    private lateinit var adapter: WatchListAdapter
    private val viewModel: WatchListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWatchListBinding.inflate(layoutInflater, container, false)

        adapter = WatchListAdapter(ClickListener {movieToWatch ->
            viewModel.onMovieClicked(movieToWatch)
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        subscribeUI(adapter, binding)

        return binding.root
    }

    private fun subscribeUI(adapter: WatchListAdapter, binding: FragmentWatchListBinding) {
        viewModel.getWatchList().observe(viewLifecycleOwner){toWatchList ->
            binding.cardView2.visibility = View.VISIBLE
            if(toWatchList.isEmpty()){
                binding.recyclerView.visibility = View.GONE
                binding.cardView2.visibility = View.VISIBLE
            }else{
                binding.cardView2.visibility = View.GONE
                adapter.submitList(toWatchList)
                binding.recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         * New way of handling Menu as of the writing of this code: June 9th 2023
         */
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.watch_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.menu_clear -> {
                        viewModel.deleteAllFromWatchList()
                        subscribeUI(adapter, binding)
                        Toast.makeText(context, "All Cleared", Toast.LENGTH_SHORT).show()
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