package com.xridwan.moviecatalogue3.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.databinding.ItemsMovieBinding
import com.xridwan.moviecatalogue3.utils.Ext.loadPoster

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private lateinit var movieFragmentCallback: MovieFragmentCallback

    fun setOnItemClickCallback(movieFragmentCallback: MovieFragmentCallback) {
        this.movieFragmentCallback = movieFragmentCallback
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                imgPoster.loadPoster(movie.posterPath)
                tvItemTitle.text = movie.title
                imgShare.setOnClickListener {
                    movieFragmentCallback.onShareClick(movie)
                }
                itemView.setOnClickListener {
                    movieFragmentCallback.onItemClicked(movie.id.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.bind(movie)
    }

    interface MovieFragmentCallback {
        fun onItemClicked(id: String)
        fun onShareClick(movie: MovieEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}