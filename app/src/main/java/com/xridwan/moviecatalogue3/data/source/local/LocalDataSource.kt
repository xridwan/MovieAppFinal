package com.xridwan.moviecatalogue3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.data.source.local.room.ContentDao
import com.xridwan.moviecatalogue3.utils.SortUtils
import com.xridwan.moviecatalogue3.utils.SortUtils.MOVIE_ENTITIES
import com.xridwan.moviecatalogue3.utils.SortUtils.TV_SHOW_ENTITIES

class LocalDataSource(private val contentDao: ContentDao) {

    //get_content
    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> =
        contentDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITIES))

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> =
        contentDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITIES))

    //get_favorite
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = contentDao.getFavoriteMovies()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> =
        contentDao.getFavoriteTvShows()

    //get_detail
    fun getDetailMovie(id: Int): LiveData<MovieEntity> = contentDao.getMovie(id)

    fun getDetailTvShow(id: Int): LiveData<TvShowEntity> = contentDao.getTvShow(id)

    //insert
    fun insertMovies(movies: List<MovieEntity>) = contentDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = contentDao.insertTvShows(tvShows)

    //set_favorite
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        contentDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        contentDao.updateTvShow(tvShow)
    }

    //update
    fun updateMovie(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        contentDao.updateMovie(movie)
    }

    fun updateTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.isFavorite = state
        contentDao.updateTvShow(tvShow)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(contentDao: ContentDao): LocalDataSource =
            instance ?: LocalDataSource(contentDao)
    }
}