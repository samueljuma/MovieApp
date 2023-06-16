package com.samueljuma.movieapp.presentation.ui.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samueljuma.movieapp.data.model.MovieToWatch
import com.samueljuma.movieapp.databinding.MovieToWatchItemBinding

class WatchListAdapter(val clickListener: ClickListener): ListAdapter<MovieToWatch, WatchListAdapter.ViewHolder>(MovieToWatchDiffCallback()) {

    class ViewHolder(val binding: MovieToWatchItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movieToWatch: MovieToWatch, clickListener: ClickListener){
            binding.movie = movieToWatch
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieToWatchItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position)!!, clickListener)
    }
}

class MovieToWatchDiffCallback: DiffUtil.ItemCallback<MovieToWatch>() {
    override fun areItemsTheSame(oldItem: MovieToWatch, newItem: MovieToWatch): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieToWatch, newItem: MovieToWatch): Boolean {
        return oldItem == newItem
    }

}

class ClickListener (val clickListener: (movie: MovieToWatch) -> Unit){
    fun onClick(movie: MovieToWatch) = clickListener(movie)
}
