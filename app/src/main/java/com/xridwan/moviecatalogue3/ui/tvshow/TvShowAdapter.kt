package com.xridwan.moviecatalogue3.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.databinding.ItemsMovieBinding
import com.xridwan.moviecatalogue3.utils.Ext.loadPoster

class TvShowAdapter :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    private lateinit var tvShowFragmentCallback: TvShowFragmentCallback

    fun setOnItemClickCallback(tvShowFragmentCallback: TvShowFragmentCallback) {
        this.tvShowFragmentCallback = tvShowFragmentCallback
    }

    inner class TvShowViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                imgPoster.loadPoster(tvShow.posterPath)
                tvItemTitle.text = tvShow.name
                imgShare.setOnClickListener {
                    tvShowFragmentCallback.onShareClick(tvShow)
                }
                itemView.setOnClickListener {
                    tvShowFragmentCallback.onItemClicked(tvShow.id.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) holder.bind(tvShow)
    }

    interface TvShowFragmentCallback {
        fun onItemClicked(id: String)
        fun onShareClick(tvShow: TvShowEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}