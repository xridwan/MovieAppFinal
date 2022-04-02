package com.xridwan.moviecatalogue3.ui.favorite.movie

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
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.databinding.FragmentMovieFavoriteBinding
import com.xridwan.moviecatalogue3.ui.detail.DetailActivity
import com.xridwan.moviecatalogue3.ui.detail.DetailViewModel
import com.xridwan.moviecatalogue3.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment(), MovieFavoriteAdapter.OnFavoriteClickCallback {

    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvMovieFavorite)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]

            movieFavoriteAdapter = MovieFavoriteAdapter()
            movieFavoriteAdapter.setOnFavoriteClickCallback(this)
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, movieObserver)

            with(binding?.rvMovieFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieFavoriteAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, movieObserver)
    }

    private val movieObserver = Observer<PagedList<MovieEntity>> { favMovies ->
        if (favMovies != null) {
            movieFavoriteAdapter.submitList(favMovies)
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
                val movieEntity = movieFavoriteAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavoriteMovie(it) }

                val snackBar =
                    Snackbar.make(requireView(), R.string.message_undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.message_ok) { _ ->
                    movieEntity?.let { viewModel.setFavoriteMovie(it) }
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
        intent.putExtra(DetailActivity.EXTRA_CONTENT, DetailViewModel.MOVIE)
        context?.startActivity(intent)
    }
}