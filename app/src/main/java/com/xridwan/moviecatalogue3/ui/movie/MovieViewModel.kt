package com.xridwan.moviecatalogue3.ui.movie

import androidx.lifecycle.ViewModel
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies(sort: String) = movieCatalogueRepository.getMovies(sort)
}