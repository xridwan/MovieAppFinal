package com.xridwan.moviecatalogue3.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity

class TvShowFavoriteViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    fun getFavoriteTvShows() = movieCatalogueRepository.getFavoriteTvShow()

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val state = !tvShowEntity.isFavorite
        movieCatalogueRepository.setFavoriteTvShow(tvShowEntity, state)
    }
}