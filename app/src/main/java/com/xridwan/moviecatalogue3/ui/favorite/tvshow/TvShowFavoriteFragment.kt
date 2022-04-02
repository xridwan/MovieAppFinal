package com.xridwan.moviecatalogue3.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.xridwan.moviecatalogue3.R
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.databinding.FragmentTvShowFavoriteBinding
import com.xridwan.moviecatalogue3.ui.detail.DetailActivity
import com.xridwan.moviecatalogue3.ui.detail.DetailViewModel
import com.xridwan.moviecatalogue3.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment(), TvShowFavoriteAdapter.OnFavoriteClickCallback {

    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: TvShowFavoriteViewModel
    private lateinit var tvShowFavoriteAdapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvTvShowFavorite)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]

            tvShowFavoriteAdapter = TvShowFavoriteAdapter()
            tvShowFavoriteAdapter.setOnFavoriteClickCallback(this)
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, tvShowObserver)

            with(binding?.rvTvShowFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowFavoriteAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, tvShowObserver)
    }

    private val tvShowObserver = Observer<PagedList<TvShowEntity>> { favTvShows ->
        if (favTvShows != null) {
            tvShowFavoriteAdapter.submitList(favTvShows)
            binding?.progressBar?.visibility = View.GONE
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = tvShowFavoriteAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavoriteTvShow(it) }

                val snackBar =
                    Snackbar.make(requireView(), R.string.message_undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.message_ok) { _ ->
                    movieEntity?.let { viewModel.setFavoriteTvShow(it) }
                }
                snackBar.show()
            }
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemCallback(id: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, id)
        intent.putExtra(DetailActivity.EXTRA_CONTENT, DetailViewModel.TV_SHOW)
        context?.startActivity(intent)
    }
}