package com.xridwan.moviecatalogue3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity

@Dao
interface ContentDao {

    //main
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    //favorite
    @Query("SELECT * FROM movie_entities WHERE isFavorite=1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE isFavorite=1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    //detail
    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovie(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_show_entities WHERE id = :id")
    fun getTvShow(id: Int): LiveData<TvShowEntity>

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>)

    //update
    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}