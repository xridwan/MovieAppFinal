package com.xridwan.moviecatalogue3.ui.tvshow

import androidx.lifecycle.ViewModel
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    fun getTvShows(sort: String) = movieCatalogueRepository.getTvShows(sort)
}