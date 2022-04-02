package com.xridwan.moviecatalogue3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.vo.Resource

interface MovieCatalogueDataSource {

    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}