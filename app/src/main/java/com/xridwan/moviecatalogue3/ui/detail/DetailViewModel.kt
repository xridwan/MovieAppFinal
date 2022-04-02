package com.xridwan.moviecatalogue3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.vo.Resource

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>
    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>

    fun setSelectedContent(id: String, category: String) {
        when (category) {
            TV_SHOW -> detailTvShow = movieCatalogueRepository.getTvShow(id.toInt())
            MOVIE -> detailMovie = movieCatalogueRepository.getMovie(id.toInt())
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            movieCatalogueRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            movieCatalogueRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailMovie() = detailMovie

    fun getDetailTvShow() = detailTvShow

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }
}