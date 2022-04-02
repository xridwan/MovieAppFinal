package com.xridwan.moviecatalogue3.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.xridwan.moviecatalogue3.R
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.databinding.ActivityDetailBinding
import com.xridwan.moviecatalogue3.ui.detail.DetailViewModel.Companion.MOVIE
import com.xridwan.moviecatalogue3.utils.Ext.loadBackdrop
import com.xridwan.moviecatalogue3.utils.Ext.onToast
import com.xridwan.moviecatalogue3.viewmodel.ViewModelFactory
import com.xridwan.moviecatalogue3.vo.Status

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var dataCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener(this)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {

            val movieId = extras.getString(EXTRA_ID)
            dataCategory = extras.getString(EXTRA_CONTENT)

            if (movieId != null) {
                viewModel.setSelectedContent(movieId, dataCategory.toString())
                setFavorite()
                if (dataCategory == MOVIE) {
                    viewModel.getDetailMovie().observe(this) { movie ->
                        when (movie.status) {
                            Status.LOADING -> binding.content.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.content.progressBar.visibility = View.GONE
                                if (movie.data != null) populateDetailMovie(movie.data)
                            }
                            Status.ERROR -> {
                                binding.content.progressBar.visibility = View.GONE
                                onToast("Terjadi kesalahan")
                            }
                        }
                    }
                } else {
                    viewModel.getDetailTvShow().observe(this) { tvShow ->
                        when (tvShow.status) {
                            Status.LOADING -> binding.content.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.content.progressBar.visibility = View.GONE
                                if (tvShow.data != null) populateDetailTvShow(tvShow.data)
                            }
                            Status.ERROR -> {
                                binding.content.progressBar.visibility = View.GONE
                                onToast("Terjadi kesalahan")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun populateDetailMovie(movieEntity: MovieEntity) {
        with(binding) {
            collapsing.title = movieEntity.title
            ivDetailImage.loadBackdrop(movieEntity.backdropPath)
            binding.content.textOverview.text = movieEntity.overview
            binding.content.textDate.text =
                resources.getString(R.string.release_date, movieEntity.releaseDate)
            binding.content.textPopularity.text =
                resources.getString(R.string.popularity, movieEntity.popularity.toString())
            binding.content.textVote.text =
                resources.getString(R.string.vote, movieEntity.voteCount.toString())
        }
    }

    private fun populateDetailTvShow(tvShowEntity: TvShowEntity) {
        with(binding) {
            collapsing.title = tvShowEntity.name
            ivDetailImage.loadBackdrop(tvShowEntity.backdropPath)
            binding.content.textOverview.text = tvShowEntity.overview
            binding.content.textDate.text =
                resources.getString(R.string.release_date, tvShowEntity.firstAirDate)
            binding.content.textPopularity.text =
                resources.getString(R.string.popularity, tvShowEntity.popularity.toString())
            binding.content.textVote.text =
                resources.getString(R.string.vote, tvShowEntity.voteCount.toString())
        }
    }

    private fun setFavorite() {
        if (dataCategory == MOVIE) {
            viewModel.getDetailMovie().observe(this) { movie ->
                when (movie.status) {
                    Status.LOADING -> binding.content.progressBar.visibility =
                        View.VISIBLE
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            binding.content.progressBar.visibility =
                                View.GONE
                            val state = movie.data.isFavorite
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        binding.content.progressBar.visibility =
                            View.GONE
                        Toast.makeText(
                            applicationContext,
                            "Terjadi kesalahan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            viewModel.getDetailTvShow().observe(this) { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> binding.content.progressBar.visibility =
                        View.VISIBLE
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            binding.content.progressBar.visibility =
                                View.GONE
                            val state = tvShow.data.isFavorite
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        binding.content.progressBar.visibility =
                            View.GONE
                        Toast.makeText(
                            applicationContext,
                            "Terjadi kesalahan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun onFabClicked() {
        if (dataCategory == MOVIE) viewModel.setFavoriteMovie()
        else viewModel.setFavoriteTvShow()
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) binding.fab.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_favorite_true
            )
        )
        else binding.fab.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_favorite_false
            )
        )
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CONTENT = "extra_content"
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fab -> onFabClicked()
        }
    }
}