package com.xridwan.moviecatalogue3.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity

class MovieFavoriteViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getFavoriteMovies() = movieCatalogueRepository.getFavoriteMovie()

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val state = !movieEntity.isFavorite
        movieCatalogueRepository.setFavoriteMovie(movieEntity, state)
    }
}